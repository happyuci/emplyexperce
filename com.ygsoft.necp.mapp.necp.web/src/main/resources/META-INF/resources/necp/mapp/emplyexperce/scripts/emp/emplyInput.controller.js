require([ 'jquery', 'ecp.service', "necp.genentity.controller", "ecp.utils.render", 'ecp.utils',"ecp.model",'ecp.utils.window',"ecp.component.dialog",
		'qzz.idatepicker', 'qzz.grid','bootstrap-select', 'ecp.component.validateBox', "datetimepicker" ,'ecp.component.comboBox'],
	function($, ecp, GenentityController, renderUtil, utils, ecpModel,$windowUtil,$dialog) {
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
						$('#emplyid').attr("readonly","readonly")
						$('#emplyname').val(content[0].emplyname);
						$('#age').val(content[0].age);
						$('#sex').comboBox(true).setValue(content[0].sex);
						var birthDate=utils.jsonStrToDate(content[0].birth);
						$("#birth").qzzdatepicker(true).setValue(utils.formatDate(birthDate, "yyyy-MM-dd"));
						$('#borth').val(content[0].borth);
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
					'ng-model' : 'birth',
					"disInput":true
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
				var option = {
					idField: 'value',
					textField: 'text',
					data :　[
						{'value':1,'text':'男'},
						{'value':0,'text':'女'}
					]
				}
				$("#birth").datetimepicker({
					autoclose: true,
					language: "zh-CN",
					format: "yyyy-mm",
					startView: "year",
					minView: "year",
					maxView: "decade"
				});
				$('#sex').comboBox(option);
				this.grid.refreshTitle(this.gridopt);
				this.bindDataSource();
				this.bindEvent();
				this.queryExperceData();
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
					var reg=/^\w+@[a-zA-Z0-9]{2,10}(?:\.[a-z]{2,4}){1,3}$/;
					if(!reg.test($('#email').val())){

					}

					//获取员工信息和工作经历
					var emplyInfo={};
					emplyInfo.sex = $('#sex').comboBox(true).getValue()==null?'':$('#sex').comboBox(true).getValue();
					emplyInfo.emplyid = $('#emplyid').val();
					emplyInfo.emplyname = $('#emplyname').val();
					emplyInfo.age = $('#age').val();
					emplyInfo.birth = $("#birth").qzzdatepicker(true).getValue();
					emplyInfo.gid = $('#gid').val();
					emplyInfo.borth = $('#borth').val();
					emplyInfo.nation = $('#nation').val();
					emplyInfo.email = $('#email').val();
					emplyInfo.emplyexperce=me.grid.getDisplayAsJson();
					var reg=/^\S/;
					if(!reg.test(emplyInfo.emplyid)){
						utils.notify("请输入员工编号");
						$('#emplyid').focus();
						return;
					}
					reg=/^([\u4e00-\u9fa5a-zA-Z]){1,10}$/;
					if(!reg.test(emplyInfo.emplyname)){
						utils.notify("请输入真实有效的姓名");
						$('#emplyname').focus();
						return;
					}
					reg=/^(?:[1-9][0-9]?|1[01][0-9]|120)$/;
					if(!reg.test(emplyInfo.age)){
						utils.notify("请输入真实年龄");
						$('#age').focus();
						return;
					}
					reg=/0|1/;
					if(!reg.test(emplyInfo.sex)){
						utils.notify("请选择性别");
						$('#sex').focus();
						return;
					}
					reg=/^\S/;
					if(!reg.test(emplyInfo.birth)){
						utils.notify("请选择生日");
						$('#birth').focus();
						return;
					}


					ecp.RemoteService.doPostAsync(
						"/necp/mapp/emplyexperce/query/emplyInfoPO/saveOrUpdateEmpInfo",
						emplyInfo, function(resp) {
							if (resp.data.flag) {
								utils.notify({ message: '保存成功。'});
								$('#gid').val(resp.data.gid);
							} else {
								utils.notify({ message: ''+resp.data.msg});
							}
						}
					);

				});


				//新增
				$("#addBtn").on('click', function() {
					me.grid.append();
				});
				//删除
				$("#delBtn").on('click', function() {
					me.grid.delRecord();
				});
				//保存退出
				// 关闭按钮点击事件
				$('#stdExitBtn').on("click", function() {
					$dialog.dialog({
						title : '您正在进行关闭页面操作', // 模态窗标题
						content : '系统不会保存您未保存的数据，确定要关闭当前页面吗？', // 模态窗内容
						isTip : true, // 标准的提示窗口
						showCloseButton : false, // 不显示关闭按钮
						otherButtons : [ '取消', '确定' ], // 增加两个按钮
						otherButtonStyles : [ 'btn-link', 'btn-primary' ], // 按钮样式,
						clickButton : function(sender, modal, index) {
							modal.modal('hide'); // 关闭模态窗
							if (index == 1) { // 确定按钮
								window.opener=null;
								window.close();
							}
						}
					});
				});

				this.dataSource.dataModel = dataModel;
				this.dataSource.bind($(".pageTopQuery"));
			},

			// 绑定事件
			bindEvent : function() {
				$('#emplyid').validateBox({
					rules : {
						expression : /^\S/,
						formatsl : '请输入员工编号！'
					}
				});
				// 姓名输入效验
				$('#emplyname').validateBox({
					rules : {
						expression : /^([\u4e00-\u9fa5a-zA-Z]){1,10}$/,
						formatsl : '请输入真实有效的姓名！'
					}
				});
				// 年龄
				$('#age').validateBox({
					rules : {
						expression :  /^(?:[1-9][0-9]?|1[01][0-9]|120)$/,
						formatsl : '请输入真实年龄！'
					}
				});
				//邮箱
				var flag=$('#email').validateBox({
					rules: {
						expression: /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/,
						formatsl: '请输入正确邮箱格式。'
					}
				});
			},


			queryExperceData: function() {
				var me = this;
				var dataModel=utils.getAllArgument();
				if(dataModel.emplyid==null){
					return;
				};
				var params = {
					pageSize: me.pager.size,
					pageNum: me.pager.page,
					example: dataModel
				};
				ecp.RemoteService.doPostAsync(
					"/necp/mapp/emplyexperce/query/emplyexpercePO/query",
					params, function(resp) {
						console.log(resp.isSuccess());
						if (resp.isError() || resp.data == null) {
							me.grid.value([]);
							me.grid.setTotalRecord(0, false);
							resp.data = resp.data ? ":" + resp.data : "";
							utils.notify("查询失败" + resp.data);
							return;
						}
						if (resp.isSuccess() && resp.data.content) {
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
