<template>
	<view class="all-menu">
		<ts-all-menu :option="option"></ts-all-menu>
		
	</view>
</template>

<script>
	var components= require('necp.tswan.mobile.components');
	var TsAllMenu = components.TsAllMenu;
	
	export default {
	    'name': 'all-menu',
		components: {
			"ts-all-menu": TsAllMenu
		},
		data() {
			return {
				option: {
							   appId: "569ce500d8b4e7eb9b7b1f28a85179e5",
							   contextPath: "/emplyexperce",
							   vipAddress: "/necp/mapp/emplyexperce"
				}
			}
		},
		onReady() {
		},
		computed: {
			
		},
		methods: {
			
		}
	}
</script>

<style>

</style>
