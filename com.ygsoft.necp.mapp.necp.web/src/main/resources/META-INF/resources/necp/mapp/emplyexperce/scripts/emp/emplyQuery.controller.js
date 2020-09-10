require([ 'jquery', 'ecp.service', "necp.genentity.controller", "ecp.utils.render", 'ecp.utils',"ecp.model",
		'qzz.idatepicker', 'qzz.grid','bootstrap-select', 'ecp.component.validateBox', "datetimepicker" ],
	function($, ecp, GenentityController, renderUtil, utils, ecpModel) {


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
				$('#sex').selectpicker();
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
				this.grid = $("#mainGrid").qzzquerygrid({
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
					me.queryData();
				});

				$('#queryBtn').click(function(){
					me.queryData();
				});
				//新增
				$("#addBtn").on('click', function() {
					window.open('emplyInput.html','_blank');
					//window.location.href="emplyInput.html";
				});
				//修改
				$("#updateBtn").on('click', function() {
					var emplyInfo={};
					emplyInfo=me.grid.dataSet.getSelectedData();
					window.open('emplyInput.html?emplyid='+emplyInfo.emplyid,'_blank');
				});
				//删除
				$('#delBtn').click(function(){
					var emplyInfo={};
					emplyInfo=me.grid.dataSet.getSelectedData();
					ecp.RemoteService.doPostAsync(
						"/necp/mapp/emplyexperce/query/emplyInfoPO/delete",
						emplyInfo, function(resp) {
							me.grid.delRecord();
						}
					);
				});

				this.dataSource.dataModel = dataModel;
				this.dataSource.bind($(".pageTopQuery"));
			},



			queryData: function() {
				var me = this;
				var dataModel = me.dataSource.dataModel;
				dataModel.sex = $('#sex').selectpicker('val');
				dataModel.emplyname = $('#emplyname').val();
				dataModel.age = $('#age').val();
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

