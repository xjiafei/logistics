webpackJsonp([10],{0:function(t,e){},"3TWb":function(t,e){},NHnr:function(t,e,n){"use strict";function o(t){n("3TWb")}Object.defineProperty(e,"__esModule",{value:!0});var a=n("7+uW"),i={name:"app",created:function(){this.$store.commit("httpTo",this.$http)}},r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"app"}},[n("router-view")],1)},s=[],c={render:r,staticRenderFns:s},u=c,l=n("VU/8"),d=o,p=l(i,u,!1,d,null,null),m=p.exports,h=n("/ocq");a.default.use(h.a);var g=new h.a({routes:[{path:"/",name:"map",component:function(t){return Promise.all([n.e(0),n.e(1)]).then(function(){return t(n("k2bH"))}.bind(null,n)).catch(n.oe)}},{path:"/login",name:"login",component:function(t){return Promise.all([n.e(0),n.e(4)]).then(function(){return t(n("6rku"))}.bind(null,n)).catch(n.oe)}},{path:"/map",name:"map",component:function(t){return Promise.all([n.e(0),n.e(1)]).then(function(){return t(n("k2bH"))}.bind(null,n)).catch(n.oe)}},{path:"/chart",name:"chart",component:function(t){return Promise.all([n.e(0),n.e(8)]).then(function(){return t(n("StQU"))}.bind(null,n)).catch(n.oe)}},{path:"/chartSon",name:"chartSon",component:function(t){return Promise.all([n.e(0),n.e(7)]).then(function(){return t(n("lGr5"))}.bind(null,n)).catch(n.oe)}},{path:"/creatOder",name:"creatOder",component:function(t){return Promise.all([n.e(0),n.e(6)]).then(function(){return t(n("VhBS"))}.bind(null,n)).catch(n.oe)}},{path:"/adminConfig",name:"AdminConfig",component:function(t){return Promise.all([n.e(0),n.e(2)]).then(function(){return t(n("ITeb"))}.bind(null,n)).catch(n.oe)}},{path:"/adminDavise",name:"adminDavise",component:function(t){return Promise.all([n.e(0),n.e(3)]).then(function(){return t(n("n3aj"))}.bind(null,n)).catch(n.oe)}},{path:"/Histroy",name:"Histroy",component:function(t){return Promise.all([n.e(0),n.e(5)]).then(function(){return t(n("nQW2"))}.bind(null,n)).catch(n.oe)}}]}),f=n("ORbq"),v=n("NYxO"),S=n("mvHQ"),T=n.n(S),L=n("NC6I"),O=n.n(L),y={token:"sssdsds",timestamp:"",sign:"",bizId:111,data:{},pager:{startNo:"",endNo:""}},D={mapListFn:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/trackQuery",e,{emulateJSON:!0}).then(function(t){n("addMapList",t)})},addOderPost:function(t,e){var n=t.commit,o=t.state;e.locateFrequency=parseInt(e.locateFrequency),e=T()(e),o.http.post("/logistics/order/insertTransportData",e,{emulateJSON:!0}).then(function(t){n("addOder",t)})},transportListFn:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/transportList",e,{emulateJSON:!0}).then(function(t){n("transportListTo",t)})},loginFn:function(t,e){var n=t.commit,o=t.state,a={userName:e.userName,passwd:O()(e.password+"IS8b9zvn")},i=new Date,r=i.getTime();y.token=o.token,y.timestamp=r,y.data=a,y.sign=O()(y.token+y.timestamp+y.data),y=T()(y),o.http.post("/user/login",y,{emulateJSON:!0}).then(function(t){console.log(t,"res"),n("updateUser",t)})},loginOutFn:function(t,e){var n=(t.commit,t.state),o=sessionStorage.getItem("token");e={userId:e.userId};var a=new Date,i=a.getTime();y.token=o,y.timestamp=i,y.data=e,y.sign=O()(y.token+y.timestamp+y.data),console.log(y),y=T()(y),n.http.post("/user/logout",y,{emulateJSON:!0}).then(function(t){console.log(t,"res")})},searchList:function(t,e){var n=t.commit,o=t.state,a={grant_type:"password",username:13713666160,password:123456,client_secret:"20170101",client_id:"PC"};o.http.post("/utransport/order/search",a,{emulateJSON:!0}).then(function(t){console.log(t,"res"),n("addMapList",t)})},history:function(t,e){var n=t.commit,o=t.state;console.log(e),o.http.post("/logistics/order/transportHistory",e,{emulateJSON:!0}).then(function(t){n("transportListTo",t)})},detail:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/transportDetail",e,{emulateJSON:!0}).then(function(t){console.log(t,"res"),n("updateDetail",t)})},checkId:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/checkId",e,{emulateJSON:!0}).then(function(t){n("transportListTo",t)})},updateTansportStatus:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/updateTransportStatus",e,{emulateJSON:!0}).then(function(t){console.log(t,"res"),n("updateStatusTo",t)})},deletefn:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/delete",e,{emulateJSON:!0}).then(function(t){console.log(t,"res"),n("updateStatusTo",t)})},getCityList:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/user/company/cityList",e,{emulateJSON:!0}).then(function(t){n("commitCityList",t)})},updateBillon:function(t,e){var n=t.commit,o=t.state;e=T()(e),o.http.post("/logistics/order/updateLadingId",e,{emulateJSON:!0}).then(function(t){n("updateStatusTo",t)})}},N={tookingAryy:D},b={token:"s5x78d47xs5x78d47xs5x78d47x25",userData:null,language:{check:"Chinese",Chinese:{id:"Chinese",header:{Login:"登陆",Exit:"退出",Contact:"联系我们",language:"语言切换",potin:"中文",potin2:"英文",logoText:"运踪宝",transport:"当前",histroy:"历史",creatOder:"新建订单",customerManagement:"用户管理",deviceManagement:"设备管理",abnormal:"异常"},public:{loadingText:"拼命加载中",loadingErorr:"数据加载异常",noData:"暂无数据",container:"设备号",plalte:"柜号",ladingIdBill:"提单号",status:"运输状态",creaedTime:"创建时间",copy:"复制",fold:"收起",exportCSV:"导出Excel",updateOn:"更新时间",deviceStatus:"设备状态",train:"火车",motor:"汽车",freighter:"轮船",Flight:"飞机",modeOfTransport:"交通工具",departure:"起运地",desitnation:"目的地",frequencyGPS:"位置发送频率",done:"确 定",mins:"分钟",oderText:"新跟踪记录创建成功",sechText:"柜号(车牌号)/提单号/设备号",month:"月",Delete:"删除",completion:"运输完成"},admin:{deviceType:"设备型号",ImeiNo:"设备号",battery:"电量",deviceOwer:"使用商家",Usages:"使用状态",Location:"设备所在地",Operation:"删除设备",BatchDelete:"删除",BatchExport:"批量导出",SearchAccount:"搜索用户名",Onwer:"用户（商家名称）",Account:"登陆账号",ResetPassword:"重置密码",DeleteAccount:"删除用户"},login:{user:"手机号登陆",password:"密码",login:"登陆",Automatic:"自动登陆"}},English:{id:"English",header:{Login:"Login",Exit:"Exit",Contact:"Contact",language:"language",potin:"Chinese",potin2:"English",logoText:"LW Cargo Tracking",transport:"Transport List",histroy:"Histroy List",creatOder:"Create new tracking data",customerManagement:"Customer Management",deviceManagement:"Device Management",abnormal:"error"},public:{loadingText:"Loading",loadingErorr:"Loading exception",noData:"No Data",container:"Container No",plalte:"Plate No",ladingIdBill:"Lading Bill No",status:"Status",creaedTime:"Created on",copy:"Copy",fold:"Fold",exportCSV:"Export CSV",updateOn:"Updated on",deviceStatus:"GPS Device Status",train:"Train",motor:"Motor",freighter:"Freighter",Flight:"Flight",modeOfTransport:"Mode of Transport",departure:"Departure",desitnation:"Desitnation",frequencyGPS:"GPS Frequency",mins:"Mins",done:"Done",oderText:"New tracking data has been created sucessfully",sechText:"Plate No / Lading Bill No / Container No",month:"month",Delete:"Delete",completion:"completion"},admin:{deviceType:"Device Type",ImeiNo:"IMEI No",battery:"Battery",deviceOwer:"Device Owner",Usages:"Usages",Location:"Location",Operation:"Operation",BatchDelete:"Batch delete",BatchExport:"Batch export",SearchAccount:"Search account",Onwer:"Onwer",Account:"Account",ResetPassword:"Reset Password",DeleteAccount:"Delete the account"},login:{user:"cell-phone number",password:"password",login:"Login",Automatic:"Automatic landing"}}},http:null,shechIput:null,nav:{logo:!0,login:!1,us:!0,language:!1,user:!1,nav:!1},transportList:null,shechList:null,updateStatus:null,addOderData:null,mapList:null,cityListData:null,Detail:null},x={stateTooking:b},k={shechDat:function(t,e){console.log(t),t.shechIput=e},updateNave:function(t,e){console.log(t),t.nav=e},addMapList:function(t,e){console.log(t),t.mapList=e},httpTo:function(t,e){console.log(t),t.http=e},addOder:function(t,e){t.addOderData=e},transportListTo:function(t,e){console.log(t),t.transportList=e},updateStatusTo:function(t,e){console.log(t),t.updateStatus=e.body},updateLanguage:function(t,e){console.log(t),t.language.check=e},commitCityList:function(t,e){console.log(t,"commit"),t.cityListData=e},updateUser:function(t,e){console.log(t,"commit"),t.userData=e.body},updateDetail:function(t,e){console.log(t,"commit"),t.Detail=e}},w={commitTooking:k};a.default.use(v.a);var C=new v.a.Store({state:x.stateTooking,mutations:w.commitTooking,actions:N.tookingAryy}),P=n("zL8q"),I=n.n(P),A=(n("tvR6"),n("Zcwg")),E=n.n(A);a.default.config.productionTip=!1,a.default.use(I.a),a.default.use(f.a),a.default.component(E.a.name,E.a),new a.default({el:"#app",router:g,store:C,template:"<App/>",components:{App:m}})},tvR6:function(t,e){}},["NHnr"]);