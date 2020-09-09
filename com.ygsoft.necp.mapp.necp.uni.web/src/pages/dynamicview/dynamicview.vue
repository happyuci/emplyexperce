<template>
    <view>
        <web-view :webview-styles="webviewStyles" :src="src"></web-view>
    </view>
</template>

<script>
    export default {
        data() {
            return {
                webviewStyles: {
                    progress: {
                        color: '#FF3333'
                    }
                },
				src: ""
            }
        },
        mounted: function () {
			let pages = getCurrentPages();
			let curPage = pages[pages.length-1];
			let options = curPage.$mp.query;
			if (options) {
				let params = '';
				for (var k in options) {
					if (!k) {
						continue;
					}
					if (k == '_innerMenuTitle') {
						uni.setNavigationBarTitle({
							title: options[k]
						});
						continue;
					}
					if (k == 'pageId') {
						params += "&id=" + options[k];
					} else {
						params += "&" + k + "=" + options[k];
					}
				}
				if (params.length) {
					params = params.substring(1);
					params = "?" + params;
				}
				
				let url = "/emplyexperceweb/assets/components/necp/dem/page/h5/index.html#/src/transfer" + params;
				let serviceAddress = getApp().globalData.serverAddress;
				if (serviceAddress) {
					url = serviceAddress + url;
				}
				this.src = url;
			}
		}
    }
</script>

<style>

</style>