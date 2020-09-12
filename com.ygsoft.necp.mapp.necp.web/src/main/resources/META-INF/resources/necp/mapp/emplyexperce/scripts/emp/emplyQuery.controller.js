require([ 'jquery', 'ecp.service', "necp.genentity.controller", "ecp.utils.render", 'ecp.utils',"ecp.model","ecp.component.dialog",'ecp.utils.window',
		'ecp.component.comboBox', 'qzz.idatepicker', 'qzz.grid','bootstrap-select', 'ecp.component.validateBox', "datetimepicker" ],
	function($, ecp, GenentityController, renderUtil, utils, ecpModel,$dialog,$windowUtil) {
		var Controller = function () {
			this.init();
		};
		Controller.prototype = {
			init: function () {
				this.pager = {
					page: 0,
					size: 20
				};
				this.gridopt = {
					colNames: [
						[
							"员工id",
							"姓名",
							"性别",
							"年龄",
							"籍贯",
							"民族",
							"生日"
						]
					],
					colModels: [
						{name: "emplyid", align: "center", width: "50"},
						{name: "emplyname", align: "center", width: "50"},
						{name: "sex", align: "center", width: "2"},
						{name: "age", width: "50", align: "center"},
						{name: "nat", align: "center", scale: 2, width: "2"},
						{name: "nation", align: "center",  width: "2"},
						{name: "birth", align: "center", width: "2",dataType:'date'}
					]
				};
				var option = {
					idField: 'value',
					textField: 'text',
					data :　[
						{'value':1,'text':'男'},
						{'value':0,'text':'女'}
					]
				}
				$('#sex').comboBox(option);
				this.render();
			},

			render: function () {
			/*	$("#birth").qzzdatepicker({
					'height' : '34',
					'underLine' : true,
					'width' : '100%',
					'color' : 'rgb(51,&amp;nbsp;51,&amp;nbsp;51)',
					'cyctype' : 'day',
					'ng-model' : 'birth'
				});*/
				this.grid = $("#mainGrid").qzzquerygrid({
					Align: "alClient",
					Align: "alClient",
					shrinkToFit: true,
					rownumbers: true,
					height:'278px',
					pager: true,
					colNames: [],
					colModels: [],
					pageSizeList: [20, 30, 40, 50]
				});
				this.grid.refreshTitle(this.gridopt);
				this.bindDataSource();
				this.queryData();
				this.bindGridEvent();
			},

			/**
			 * 绑定数据源.
			 */
			bindDataSource: function() {
				var me = this;
				this.dataSource = new ecpModel.DataSource();
				var arg = utils.getAllArgument();
				var dataModel = {};
				$.each(arg, function(k, v) {
					if (k != "url" && k != "_h") {
						dataModel[k] = v;
					}
				});
				$("#sex").comboBox(true).bind("change", function() {
					me.queryData();
				});

				$('#saveBtn').click(function(){
					me.queryData();
				});

				$(".clearBtn").click(function(e) {
					me.queryData();
				});

				$('#queryBtn').click(function(){
					me.queryData();
				});
				//新增
				$("#addBtn").on('click', function() {
					//window.open('emplyInput.html','_blank');
					$windowUtil.openWindow('emplyInput.html','ECP');
					//window.location.href="emplyInput.html";
				});
				//修改
				$("#updateBtn").on('click', function() {
					var emplyInfo={};
					emplyInfo=me.grid.dataSet.getSelectedData();
					window.open(utils.encodeSearch('emplyInput.html?emplyid='+emplyInfo.emplyid),'_blank');

				});
				//删除
				$('#delBtn').click(function(){
					var emplyInfo={};
					emplyInfo=me.grid.dataSet.getSelectedData();
					 $dialog.show({
						title:'提示',
						content:'是否删除当前数据？',
						showCloseButton:false,
						isTip:true,
						otherButtons:['确定','取消'],
						otherButtonStyles:['btn-default', 'btn-default', 'btn-primary'],
						clickButton : function(sender, modal, index){
							if(index == 1){   //'取消'按钮
								modal.modal('hide');
							}
							if(index == 0){
								ecp.RemoteService.doPostAsync(
									"/necp/mapp/emplyexperce/query/emplyInfoPO/delete",
									emplyInfo,
									function(resp) {
										if (resp.isError()) {
											resp.data = resp.data ? ":" + resp.data : "";
											utils.notify("删除失败" + resp.data);
											return;
										}
										utils.notify("删除成功");
										me.grid.delRecord();
										modal.modal('hide');

									}
								);

							}
						}
					});
				});

				this.dataSource.dataModel = dataModel;
				this.dataSource.bind($(".pageTopQuery"));
			},

			bindGridEvent: function() {
				this.grid.dataSet.bind("onFormat_sex", function (node, fieldName, dataType, value, metaItem) {
					if(this.getValue(fieldName) === 1) {
						return '男';
					} else {
						return '女';
					}
				});
			},

			queryData: function() {
				var me = this;
				var dataModel = me.dataSource.dataModel;
				dataModel.sex = $('#sex').comboBox(true).getValue();
				dataModel.emplyname = $('#emplyname').val();
				dataModel.age = $('#age').val();
				//dataModel.birth = utils.formatDate($("#birth").datetimepicker("getDate"), "yyyy-MM-dd")
				var params = {
					pageSize: me.pager.size,
					pageNum: me.pager.page,
					example: dataModel
				};
				ecp.RemoteService.doPostAsync(
					"/necp/mapp/emplyexperce/query/emplyInfoPO/query",
					params, function(resp) {
						console.log(resp);
						console.log(resp.isSuccess());
						if (resp.isError() || resp.data == null) {
							me.grid.value([]);
							me.grid.setTotalRecord(0, false);
							resp.data = resp.data ? ":" + resp.data : "";
							utils.notify("查询失败" + resp.data);
							return;
						}
						if (resp.isSuccess() && resp.data) {
							var data = resp.data;
							me.grid.value(data.content);
							me.grid.setTotalRecord(data.totalElements, false);
						}
						me.grid.refreash(true);
						me.grid.doResize();

					}
				);
			},

		}
		var c = new Controller();
	})

