<template>
	<view>
		<view @click="open">
			<slot></slot>
		</view>
		<view
		 class="popup"
		 :class="{ open: popupOpen }"
		>
			<view
			 class="popup-mask"
			 @click.prevent="onCancel"
			></view>
			<view class="popup-wrap">
				<view class="popup-header">
					<view
					 class="btn"
					 @click="onCancel"
					>取消</view>
					<view
					 class="tit"
					 v-if="range && calendar"
					>
						{{ lang.chosen }}
						<text class="day">{{ chosenDays }}</text>
						{{ lang.day }}
					</view>
					<view
					 class="btn btn-confirm"
					 @click="onChange"
					>确定</view>
				</view>
				<view
				 class="popup-content"
				 :class="{'popup-content-range': range && !calendar, 'popup-content-range-calendar': range && calendar, 'popup-content-calendar': !range && calendar}"
				>
					<ecp-datetime-picker-view
					 v-if="!range && !calendar"
					 :type="type"
					 :mode="mode"
					 :start="start"
					 :end="end"
					 :value.sync="pickerValue"
					></ecp-datetime-picker-view>
					<ecp-datetime-picker-range-view
					 v-if="range && !calendar"
					 :type="type"
					 :mode="mode"
					 :start="start"
					 :end="end"
					 :startTxt="startTxt"
					 :endTxt="endTxt"
					 :startValue.sync="pickerStartValue"
					 :endValue.sync="pickerEndValue"
					></ecp-datetime-picker-range-view>
					<ecp-scroll-calendar
					 ref="calendar"
					 v-if="range && calendar"
					 :type="type"
					 :mode="mode"
					 :range="true"
					 :start="start"
					 :end="end"
					 :startValue.sync="pickerStartValue"
					 :endValue.sync="pickerEndValue"
					></ecp-scroll-calendar>
					<ecp-calendar
					 v-if="!range && calendar"
					 :type="type"
					 :start="start"
					 :end="end"
					 :value.sync="pickerValue"
					></ecp-calendar>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import ecpDatetimePickerView from './ecp-datetime-picker-view.vue'
	import ecpDatetimePickerRangeView from './ecp-datetime-picker-range-view.vue'
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue'
	import ecpCalendar from '@/lib/ecp/components/ecp-calendar/ecp-calendar.vue'
	import ecpScrollCalendar from '@/lib/ecp/components/ecp-scroll-calendar/ecp-scroll-calendar.vue'
	import {
		parse,
		format,
		startOfDay,
		differenceInCalendarDays
	} from 'date-fns'

	const toUpperCase = k => k.slice(0, 1).toUpperCase() + k.slice(1)

	let lang = {
		chosen: '已选',
		day: '天'
	}

	const today = startOfDay(new Date())

	export default {
		components: {
			ecpDatetimePickerView,
			ecpDatetimePickerRangeView,
			uniCalendar,
			ecpCalendar,
			ecpScrollCalendar
		},
		props: {
			type: {
				type: String,
				validator: v => !v || ['ecpDate', 'string', 'date'].indexOf(v) > -1
			},
			mode: {
				type: [String, Function],
				default: () => 'day'
			},
			range: Boolean,
			calendar: Boolean,
			start: [String, Date],
			end: [String, Date],
			value: [String, Date],
			startValue: [String, Date],
			endValue: [String, Date],
			startTxt: String,
			endTxt: String
		},
		data() {
			return {
				popupOpen: false,
				lang,
				...['Value', 'StartValue', 'EndValue'].reduce((a, k) => {
					a['picker' + k] = a['old' + k] = ''
					return a
				}, {})
			}
		},
		computed: {
			chosenDays() {
				return this.range && this.calendar && this.pickerStartValue && this.pickerEndValue ? this.$refs.calendar.getChosenDays() : 0
			}
		},
		methods: {
			open() {
				this.popupOpen = true;
			},
			close() {
				this.popupOpen = false;
			},
			onChange(e) {
				this.close();
				this.updateValue();
				if (['value', 'startValue', 'endValue'].some(k => {
						let propName = 'picker' + toUpperCase(k)
						return this.type === 'date' ? +this[k] !== +this[propName] : this[k] !== this[propName]
					})) {
					this.$emit('change', this.range ? {
						start: this.pickerStartValue,
						end: this.pickerEndValue
					} : this.pickerValue)
					if (this.range) {
						this.$emit('update:startValue', this.pickerStartValue)
						this.$emit('update:endValue', this.pickerEndValue)
					} else {
						this.$emit('input', this.pickerValue)
						this.$emit('update:value', this.pickerValue)
					}
				}
			},
			onCancel() {
				this.close();
				this.resetValue();
				this.$emit('cancel');
			},
			setValue(k, v) {
				let propName = toUpperCase(k)
				this['picker' + propName] = this['old' + propName] = v
			},
			initValue() {
				['value', 'startValue', 'endValue'].forEach(k => this.setValue(k, this[k]), this)
			},
			...['update', 'reset'].reduce((a, k, i) => {
				a[k + 'Value'] = function() {
					['value', 'startValue', 'endValue'].forEach(v => {
						let propName = toUpperCase(v)
						let target = i ? 'picker' : 'old'
						let source = i ? 'old' : 'picker'
						this[target + propName] = this[source + propName]
					})
				}
				return a
			}, {}),
			uniPickerValue(v) {
				if (v) {
					return v
				}
				let cur = new Date()
				let value = format(cur, this.mode === 'day' ? 'yyyy-MM-dd' : 'HH:mm');
				if (this.start && value < this.start) {
					value = this.start
				}
				if (this.end && value > this.end) {
					value = this.end
				}
				return value
			}
		},
		watch: {
			...['mode', 'calendar', 'type', 'range', 'start', 'end', 'value', 'startValue', 'endValue'].reduce((a, k) => {
				a[k] = function(v) {
					if (['value', 'startValue', 'endValue'].indexOf(k) > -1) {
						this.setValue(k, v)
					} else {
						this.initValue()
					}
				}
				return a
			}, {})
		},
		created() {
			this.initValue()
		}
	}
</script>

<style>
	.popup {
		position: fixed;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		visibility: hidden;
		transition: visibility 0.2s linear;
		z-index: 999;
	}

	.popup.open {
		visibility: visible;
	}

	.popup-mask {
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.3);
		opacity: 0;
		transition: opacity 0.2s linear;
	}

	.popup.open .popup-mask {
		opacity: 1;
	}

	.popup-wrap {
		background-color: #fff;
		position: absolute;
		left: 0;
		bottom: 0;
		width: 100%;
		transform: translateY(100%);
		transition: transform 0.2s linear;
	}

	.popup.open .popup-wrap {
		transform: translateY(0);
	}

	.popup-header {
		height: 42px;
		display: flex;
		justify-content: space-between;
		border-bottom: 1px solid #ddd;
	}

	.popup-header .tit {
		flex: auto;
		color: #999;
		text-align: center;
		font-size: 14px;
		line-height: 42px;
	}

	.popup-header .day {
		color: #ff6147;
	}

	.popup-header .btn {
		flex: none;
		padding: 0 15px;
		color: #666;
		line-height: 42px;
		font-size: 12px;
	}

	.popup-header .btn-confirm {
		color: #00A9F0;
		font-size: 14px;
	}

	.popup-content {
		height: 170px;
	}

	.popup-content-range {
		height: 221px;
	}

	.popup-content-range-calendar {
		height: 430px;
	}

	.popup-content-calendar {
		height: auto;
	}
</style>
