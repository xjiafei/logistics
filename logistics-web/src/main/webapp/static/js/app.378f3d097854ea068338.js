webpackJsonp([12],{0:function(t,e){},AMmF:function(t,e,n){"use strict";var o=n("NC6I"),a=n.n(o),i={sessionStorageSav:function(t,e){sessionStorage.setItem(t,e)},sessionStorageGet:function(t){return sessionStorage.getItem(t)||""},senImg:function(t,e){var n=document.getElementById(t).files[0],o=new FileReader;o.readAsDataURL(n),o.onload=e},timeCheng:function(t){var e=new Date(t),n=e.getFullYear()+"-",o=(e.getMonth()+1<10?"0"+(e.getMonth()+1):e.getMonth()+1)+"-",a=e.getDate()+" ",r=e.getHours(),c=e.getMinutes(),s=e.getSeconds(),u=e.getDay();u=i.wek(u),c<10&&(c="0"+c.toString()),o<10&&(o="0"+o.toString()),a<10&&(a="0"+a.toString()),r<10&&(r="0"+r.toString());return{ymd:n+o+a,hms:(r+=":")+c,times:n+o+a+r+c,week:u,md:o+a,m:o,d:a,s:s,y:n}},dataChange:function(t){var e,n,o,a,i;n=(e=t?new Date(t):new Date).getDay(),i=e.getTime();var r=(a=this.timeCheng(i)).times,c=a.y,s=a.m,u=a.d;return a=a.ymd,o=this.timeCheng(i+864e5),o=o.ymd,{today:a,week:n,nexDate:o,time:i,ymdTiem:r,y:c,m:s,d:u}},weekDate:function(t,e,n){n||(n=7);var o,a;a=new Date(t).getTime();for(var i=[],r=0;r<n;r++){var c=864e5*r;"add"==e?(o=this.timeCheng(a+c),i.push(o)):(o=this.timeCheng(a-c),i.unshift(o))}return i},wek:function(t){var e;switch(t=parseInt(t)){case 1:e="星期一";break;case 2:e="星期二";break;case 3:e="星期三";break;case 4:e="星期四";break;case 5:e="星期五";break;case 6:e="星期六";break;case 0:e="星期日"}return e},calendars:function(){},tmieOut:function(t,e,n){var o=(n=n)-t;o=(e=6e4*parseInt(e))-o;var a=parseInt(o/6e4),i=o%6e4;return i=parseInt(i/1e3),{timeM:a,timeS:i}},sortData:function(t,e,n){return t.sort(i.sortDataParm(e,n)),t},sortDataParm:function(t,e){return function(n,o){return e?n[t]<o[t]:n[t]>o[t]}},parmToken:function(t){var e=(new Date).getTime(),n=null,o=null;console.log(t),t.page&&(n=t.page,o=t.pageSize,delete t.page,delete t.pageSize);return{token:sessionStorage.getItem("token")||"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJjdXN0b21lciIsImlhdCI6MTUxMzA3NDEyMSwic3ViIjoiMCIsImV4cCI6MTUxNDU0NTM1MCwibmJmIjoxNTEzMDc0MTIxfQ.RonwXddXdXeZOp3-KhpwJxsTEOIFX_zigiE5BPGJo7w",timestamp:e,sign:a()(sessionStorage.getItem("token")+e+t),bizId:111,data:t||{},pager:{startNo:n||null,endNo:o||null}}}};e.a={publicLo:i}},NHnr:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});n("j1ja");var o=n("7+uW"),a={render:function(){var t=this.$createElement,e=this._self._c||t;return e("div",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]},i=n("VU/8")({name:"app",created:function(){this.$store.commit("httpTo",this.$http)}},a,!1,function(t){n("lkWx")},null,null).exports,r=n("/ocq");o.default.use(r.a);var c=new r.a({routes:[{path:"/",name:"index",component:function(t){return Promise.all([n.e(0),n.e(1)]).then(function(){return t(n("k2bH"))}.bind(null,n)).catch(n.oe)}},{path:"/login",name:"login",component:function(t){return Promise.all([n.e(0),n.e(3)]).then(function(){return t(n("6rku"))}.bind(null,n)).catch(n.oe)}},{path:"/map",name:"map",component:function(t){return Promise.all([n.e(0),n.e(1)]).then(function(){return t(n("k2bH"))}.bind(null,n)).catch(n.oe)}},{path:"/chart",name:"chart",component:function(t){return Promise.all([n.e(0),n.e(8)]).then(function(){return t(n("StQU"))}.bind(null,n)).catch(n.oe)}},{path:"/chartSon",name:"chartSon",component:function(t){return Promise.all([n.e(0),n.e(7)]).then(function(){return t(n("lGr5"))}.bind(null,n)).catch(n.oe)}},{path:"/creatOder",name:"creatOder",component:function(t){return Promise.all([n.e(0),n.e(9)]).then(function(){return t(n("VhBS"))}.bind(null,n)).catch(n.oe)}},{path:"/adminConfig",name:"AdminConfig",component:function(t){return Promise.all([n.e(0),n.e(2)]).then(function(){return t(n("ITeb"))}.bind(null,n)).catch(n.oe)}},{path:"/adminDavise",name:"adminDavise",component:function(t){return Promise.all([n.e(0),n.e(5)]).then(function(){return t(n("n3aj"))}.bind(null,n)).catch(n.oe)}},{path:"/Histroy",name:"Histroy",component:function(t){return Promise.all([n.e(0),n.e(10)]).then(function(){return t(n("nQW2"))}.bind(null,n)).catch(n.oe)}},{path:"/adminAddUser",name:"adminAddUser",component:function(t){return Promise.all([n.e(0),n.e(6)]).then(function(){return t(n("C7KB"))}.bind(null,n)).catch(n.oe)}},{path:"/cityConfig",name:"cityConfig",component:function(t){return Promise.all([n.e(0),n.e(4)]).then(function(){return t(n("DeDE"))}.bind(null,n)).catch(n.oe)}}]}),s=n("ORbq"),u=n("NYxO"),l=n("mvHQ"),p=n.n(l),d=n("NC6I"),m=n.n(d),h=n("AMmF"),g={token:"sssdsds",timestamp:"",sign:"",bizId:111,data:{},pager:{startNo:"",endNo:""}},f={tookingAryy:{mapListFn:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/trackQuery",a,{emulateJSON:!0}).then(function(t){n("addMapList",t)}).catch(function(t){n("addMapList",null)})},addOderPost:function(t,e){var n=t.commit,o=t.state;e.locateFrequency=parseInt(e.locateFrequency);var a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/insertTransportData",a,{emulateJSON:!0}).then(function(t){n("addOder",t)}).catch(function(t){n("addOder",null)})},transportListFn:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),console.log(a),o.http.post("/logistics/order/transportList",a,{emulateJSON:!0}).then(function(t){n("transportListTo",t)}).catch(function(t){n("transportListTo",null)})},loginFn:function(t,e){var n=t.commit,o=t.state,a={userName:e.userName,passwd:m()(e.password+"IS8b9zvn")},i=(new Date).getTime();g.token=o.token,g.timestamp=i,g.data=a,g.sign=m()(g.token+g.timestamp+g.data),o.http.post("/user/login",p()(g),{emulateJSON:!0}).then(function(t){n("updateUser",t)})},loginOutFn:function(t,e){var n=t.commit,o=t.state;e={userId:e.userId};var a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/user/logout",a,{emulateJSON:!0}).then(function(t){0==t.body.status?n("updateStatusTo",{body:"out"}):n("updateStatusTo",{body:"outErorr"})})},searchList:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);o.http.post("logistics/order/search",a).then(function(t){n("transportListTo",t)}).catch(function(t){n("transportListTo",null)})},history:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/transportHistory",a,{emulateJSON:!0}).then(function(t){n("transportListTo",t)}).catch(function(t){n("transportListTo",null)})},detail:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/transportDetail",a,{emulateJSON:!0}).then(function(t){n("updateDetail",t)}).catch(function(t){n("updateDetail",null)})},checkId:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/checkId",a,{emulateJSON:!0}).then(function(t){n("transportListTo",t)}).catch(function(t){n("transportListTo",null)})},checkNumber:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),console.log(a),o.http.post("/logistics/order/checkNumber",a,{emulateJSON:!0}).then(function(t){n("transportListTo",t)}).catch(function(t){n("transportListTo",null)})},updateTansportStatus:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/updateTransportStatus",a,{emulateJSON:!0}).then(function(t){n("updateStatusTo",t)}).catch(function(t){n("updateStatusTo",{body:null})})},deletefn:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/delete",a,{emulateJSON:!0}).then(function(t){n("updateStatusTo",t)})},getCityList:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/user/company/cityList",a,{emulateJSON:!0}).then(function(t){n("commitCityList",t)}).catch(function(t){n("commitCityList",null)})},updateBillon:function(t,e){var n=t.commit,o=t.state,a=h.a.publicLo.parmToken(e);a=p()(a),o.http.post("/logistics/order/updateLadingId",a,{emulateJSON:!0}).then(function(t){n("updateStatusTo",t)}).catch(function(t){n("updateStatusTo",{body:null})})}}},T={token:"eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJsb2dpbiIsImlhdCI6MTUxMzA3NDEyMSwic3ViIjoiLTEiLCJleHAiOjE1MTQ1NDUzNTAsIm5iZiI6MTUxMzA3NDEyMX0.Zc88CxsE8n_N5BNj9zMWH9zyENksT1IfdGcaKmjGGkY",userData:null,language:{check:"Chinese",Chinese:{id:"Chinese",header:{Login:"登陆",Exit:"退出",Contact:"联系我们",language:"语言切换",potin:"中文",potin2:"英文",logoText:"运踪宝",transport:"当前",histroy:"历史",creatOder:"新建订单",customerManagement:"用户管理",deviceManagement:"设备管理",abnormal:"异常"},public:{loadingText:"拼命加载中",loadingErorr:"数据加载异常",noData:"暂无数据",container:"设备号",plalte:"柜号",ladingIdBill:"提单号",status:"运输状态",creaedTime:"创建时间",copy:"复制",fold:"收起",exportCSV:"导出Excel",updateOn:"更新时间",deviceStatus:"设备状态",train:"火车",motor:"汽车",freighter:"轮船",Flight:"飞机",modeOfTransport:"交通工具",departure:"起运地",desitnation:"目的地",frequencyGPS:"位置发送频率",done:"确 定",mins:"分钟",oderText:"新跟踪记录创建成功",sechText:"柜号(车牌号)/提单号/设备号",month:"月",Delete:"删除",completion:"运输完成"},admin:{deviceType:"设备型号",ImeiNo:"设备号",battery:"电量",deviceOwer:"使用商家",Usages:"使用状态",Location:"设备所在地",Operation:"删除设备",BatchDelete:"删除",BatchExport:"批量导出",SearchAccount:"搜索用户名",Onwer:"用户（商家名称）",Account:"登陆账号",ResetPassword:"重置密码",DeleteAccount:"删除用户"},login:{user:"手机号登陆",password:"密码",login:"登陆",Automatic:"自动登陆"}},English:{id:"English",header:{Login:"Login",Exit:"Exit",Contact:"Contact",language:"language",potin:"Chinese",potin2:"English",logoText:"LW Cargo Tracking",transport:"Transport List",histroy:"Histroy List",creatOder:"Create new tracking data",customerManagement:"Customer Management",deviceManagement:"Device Management",abnormal:"error"},public:{loadingText:"Loading",loadingErorr:"Loading exception",noData:"No Data",container:"Container No",plalte:"Plate No",ladingIdBill:"Lading Bill No",status:"Status",creaedTime:"Created on",copy:"Copy",fold:"Fold",exportCSV:"Export CSV",updateOn:"Updated on",deviceStatus:"GPS Device Status",train:"Train",motor:"Motor",freighter:"Freighter",Flight:"Flight",modeOfTransport:"Mode of Transport",departure:"Departure",desitnation:"Desitnation",frequencyGPS:"GPS Frequency",mins:"Mins",done:"Done",oderText:"New tracking data has been created sucessfully",sechText:"Plate No / Lading Bill No / Container No",month:"month",Delete:"Delete",completion:"completion"},admin:{deviceType:"Device Type",ImeiNo:"IMEI No",battery:"Battery",deviceOwer:"Device Owner",Usages:"Usages",Location:"Location",Operation:"Operation",BatchDelete:"Batch delete",BatchExport:"Batch export",SearchAccount:"Search account",Onwer:"Onwer",Account:"Account",ResetPassword:"Reset Password",DeleteAccount:"Delete the account"},login:{user:"cell-phone number",password:"password",login:"Login",Automatic:"Automatic landing"}}},http:null,shechIput:null,nav:{logo:!0,login:!0,us:!0,language:!0,user:!1,nav:!1},transportList:null,shechList:null,updateStatus:null,addOderData:null,mapList:null,cityListData:null,Detail:null},L={commitTooking:{shechDat:function(t,e){console.log(t),t.shechIput=e},updateNave:function(t,e){console.log(t),t.nav=e},addMapList:function(t,e){console.log(t),t.mapList=e},httpTo:function(t,e){console.log(t),t.http=e},addOder:function(t,e){t.addOderData=e},transportListTo:function(t,e){console.log(t),t.transportList=e},updateStatusTo:function(t,e){console.log(t),t.updateStatus=e.body},updateLanguage:function(t,e){console.log(t),t.language.check=e},commitCityList:function(t,e){console.log(t,"commit"),t.cityListData=e},updateUser:function(t,e){console.log(t,"commit"),t.userData=e.body},updateDetail:function(t,e){console.log(t,"commit"),t.Detail=e},loginOut:function(t,e){t.token="s5x78d47xs5x78d47xs5x78d47x25",t.shechIput=null,t.mapList=null,t.cityListData=null,t.transportList=null,t.updateStatus=null,t.userData=null,console.log(t,"commit")}}};o.default.use(u.a);var y=new u.a.Store({state:T,mutations:L.commitTooking,actions:f.tookingAryy}),b=n("zL8q"),S=n.n(b),D=(n("tvR6"),n("Zcwg")),k=n.n(D);o.default.config.productionTip=!1,o.default.use(S.a),o.default.use(s.a),o.default.component(k.a.name,k.a),new o.default({el:"#app",router:c,store:y,template:"<App/>",components:{App:i}})},lkWx:function(t,e){},tvR6:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.378f3d097854ea068338.js.map