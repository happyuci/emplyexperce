require([ 'jquery', 'ecp.service', "necp.genentity.controller", "ecp.utils.render", 'ecp.utils',"ecp.model",'ecp.utils.window',
		'qzz.idatepicker', 'qzz.grid','bootstrap-select', 'ecp.component.validateBox', "datetimepicker" ],
	function($, ecp, GenentityController, renderUtil, utils, ecpModel,$windowUtil) {


		var Controller = function () {
			this.init();
			//初始化基本信息
			var dataModel=utils.getAllArgument();
			if(dataModel.emplyid==null){
				return;
			}else{
				ecp.RemoteService.doPostAsync(
					"/necp/mapp/emplyexperce/query/emplyInfoPO/findByEmpId ",
					dataModel, function(resp) {
						var data=resp.data;
						var content = data;
						$('#emplyid').val(content[0].emplyid);
						$('#emplyname').val(content[0].emplyname);
						$('#age').val(content[0].age);
						//$('#sex').selectpicker(content[0].sex);
						//$('#sex').selectpicker('refresh');
						$('#birth').val(content[0].birth);
						$('#nat').val(content[0].nat);
						$('#nation').val(content[0].nation);
						$('#email').val(content[0].email);
						$('#gid').val(content[0].gid);
					}
				);
			}

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
							"入职日期",
							"离职日期",
							"工作单位",
							"职务",
							"备注"
						]
					],
					colModels: [
						{name: "stime", align: "center", width: "50","dataType":'date'},
						{name: "etime", align: "center", width: "50","dataType":'date'},
						{name: "company", align: "center", width: "2"},
						{name: "composition", width: "50", align: "center"},
						{name: "rmark", align: "center", scale: 2, width: "2"}
					]
				};
				this.render();
			},

			render: function () {
				$("#birth").qzzdatepicker({
					'height' : '34',
					'underLine' : true,
					'width' : '100%',
					'color' : 'rgb(51,&amp;nbsp;51,&amp;nbsp;51)',
					'cyctype' : 'day',
					'ng-model' : 'birth'
				});
				this.grid = $("#mainGrid").qzzgrid({
					Align: "alClient",
					Align: "alClient",
					shrinkToFit: true,
					rownumbers: true,
					pager: true,
					colNames: [],
					colModels: [],
					pageSizeList: [20, 30, 40, 50]
				});
				this.grid.refreshTitle(this.gridopt);
				this.bindDataSource();
				this.queryData();
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

				$('#saveBtn').click(function(){
					//获取员工信息和工作经历
					var emplyInfo={};
					emplyInfo.sex = $('#sex').selectpicker('val');
					emplyInfo.emplyid = $('#emplyid').val();
					emplyInfo.emplyname = $('#emplyname').val();
					emplyInfo.age = $('#age').val();
					emplyInfo.gid = $('#gid').val();
					emplyInfo.emplyexperce=me.grid.getDisplayAsJson();
					ecp.RemoteService.doPostAsync(
						"/necp/mapp/emplyexperce/query/emplyInfoPO/saveOrUpdateEmpInfo",
						emplyInfo, function(resp) {
							console.log(resp);
						}
					);
				});

				$('#queryBtn').click(function(){
					me.queryData();
				});
				//新增
				$("#addBtn").on('click', function() {
					me.grid.append();
				});
				//删除
				$("#delBtn").on('click', function() {
					me.grid.delRecord();
				});
				//推退出
				$("#stdExitBtn").on('click', function() {
					$windowUtil.registAutoClose(100);
				});

				this.dataSource.dataModel = dataModel;
				this.dataSource.bind($(".pageTopQuery"));
			},



			queryData: function() {
				var me = this;
				var dataModel=utils.getAllArgument();
				var params = {
					pageSize: me.pager.size,
					pageNum: me.pager.page,
					example: dataModel
				};
				ecp.RemoteService.doPostAsync(
					"/necp/mapp/emplyexperce/query/emplyexpercePO/query",
					params, function(resp) {
						//console.log(resp);
						//console.log(resp.isSuccess());
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

