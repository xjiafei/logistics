webpackJsonp([8],{StQU:function(t,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var e=s("mvHQ"),i=s.n(e),n=s("Dd8w"),o=s.n(n),r=s("orAT"),c=s("AMmF"),l=s("V33R"),d=s.n(l),u=s("nDrS"),p=s.n(u),h=s("NYxO"),g=s("EMXe"),v=s("Iu90"),m=s.n(v),_=(s("LPE6"),s("+EAe")),y=s.n(_),f=(s("VeD2"),s("xnxj")),C=s.n(f),D=(s("tZ6o"),s("uL0D")),b=s.n(D),S=(s("YUF+"),{name:"my-Chart",created:function(){var t=sessionStorage.getItem("user");t&&(t=JSON.parse(t),this.user=t)},data:function(){return{errorDate:null,dialogVisible:!1,fromData:{id:null,recyclePosition:{country:null,city:null,address:null},transportStatus:1,deviceNumber:null},input:null,year:null,pages:{page:1,pageSize:10,cunt:null},list:[{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2},{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2}],listData:[],loading:!1}},methods:o()({},Object(h.b)({transportListpost:"transportListFn",updateTansportStatuspost:"updateTansportStatus",delPost:"deletefn",checkID:"checkNumber",mapListFn:"mapListFn"}),{handleCurrentChange:function(t){this.pages.page=parseInt(t),this.input?this.saechFn():this.saechList()},saechList:function(){this.loading=!0;var t={id:this.user.companyId,page:this.pages.page,pageSize:this.pages.pageSize};this.transportListpost(t)},saechFn:function(){if(this.loading=!0,this.input){var t={code:this.input};this.$store.commit("shechDat",this.input),this.checkID(t)}else{this.pages.page=1,this.pages.pageSize=10;t={id:this.user.companyId,page:this.pages.page,pageSize:this.pages.pageSize};this.transportListpost(t)}},listFn:function(t){if(this.loading=!1,this.listData=[],t.code)return 1==t.code&&(this.errorDate="暂无此类订单"),void(-1==t.code&&(this.errorDate="数据请求异常，请稍后重试"));if(t.body.count){var a=t.body.count;this.pages.cunt=Math.ceil(a/this.pages.pageSize)}t=t.body.data;var s=[];for(var e in t){var i,n,o;n=t[e].createTime?c.a.publicLo.timeCheng(t[e].createTime):{ymd:null,hms:null,times:null,s:null,d:null,m:null,md:null,week:null,y:null};var r=c.a.publicLo.dataChange();if(r.y+r.m==n.y+n.m){var l=parseInt(r.d),d=parseInt(n.d);l==d?o="Chinese"==this.languageData.id?"今天":"Today":l-d==1&&(o="Chinese"==this.languageData.id?"昨天":"Yesterday")}i=this.ransportSwitch(t[e].transportType);var u=t[e].startPosition.city.split("_"),h=t[e].endPosition.city.split("_");t[e].startPosition.state=t[e].startPosition.country+"_"+u[1],t[e].endPosition.state=t[e].endPosition.country+"_"+h[1];var g,v=p.a[t[e].startPosition.country][t[e].startPosition.state][t[e].startPosition.city],m=p.a[t[e].endPosition.country][t[e].endPosition.state][t[e].endPosition.city];v||(v={CN:"",EN:""}),m||(m={CN:"",EN:""}),g=t[e].deviceOnff&&1==t[e].deviceOnff?{CN:"开",EN:"ON"}:0==t[e].deviceOnff?{CN:"关",EN:"OFF"}:{CN:"异常",EN:"error"},t[e].newAddress?t[e].newAddress.length>9&&(t[e].newAddress=t[e].newAddress.substring(0,8)+"..."):(t[e].newAddress="未请求到数据","English"==this.languageData.id&&(t[e].newAddress="No Data"));var _={EN:"",CN:""};0==t[e].transportStatus&&(_={EN:"In transit",CN:"运输中"}),1==t[e].transportStatus&&(_={EN:"Finished",CN:"运输完成"}),n.m=parseInt(n.m);(u={id:t[e].id,containerId:t[e].containerId,ladingId:t[e].billNumber,deviceId:t[e].deviceNumber,deviceOnff:t[e].deviceOnff,postCompilt:t[e].endPosition,deviceOnfftext:g,startPosition:v.CN,endPosition:m.CN,startPositionEn:v.EN,endPositionEn:m.EN,transportStatus:t[e].transportStatus,transportStatustextC:_.CN,transportStatustextE:_.EN,transportType:t[e].transportType,deviceStatus:0,time:n.hms,year:parseInt(n.y),month:n.m,monthEN:1==n.m?"Jan":2==n.m?"Feb":3==n.m?"Mar":4==n.m?"Apr":5==n.m?"May":6==n.m?"Jun":7==n.m?"jul":8==n.m?"Aug":9==n.m?"Sep":10==n.m?"Oct":11==n.m?"Nov":"Dec",data:n.d,dateText:o,transportTypeText:i.type,transportTypeTextEn:i.typeEn,icon:i.icon,now:t[e].newAddress,ymd:n.ymd+" "+n.hms}).startPosition.length>4&&(u.startPosition=u.startPosition.substring(0,4)+"..."),u.endPosition.length>4&&(u.endPosition=u.endPosition.substring(0,4)+"..."),u.startPositionEn.length>8&&(u.startPositionEn=u.startPositionEn.substring(0,8)+"..."),u.endPositionEn.length>8&&(u.endPositionEn=u.endPositionEn.substring(0,8)+"..."),0==e?this.year=u.year:this.year==u.year&&(u.year=""),s.push(u)}this.listData=s},ransportSwitch:function(t){var a;switch(t){case 0:a={type:"火车",icon:b.a,typeEn:"Train"};break;case 1:a={type:"汽车",icon:m.a,typeEn:"Motor"};break;case 3:a={type:"轮船",icon:C.a,typeEn:"Freighter"};break;case 2:a={type:"飞机",icon:y.a,typeEn:"Flight"};break;default:a={type:"异常",icon:m.a}}return a},updatStatusFn:function(t){this.fromData.id=t.id,this.fromData.deviceNumber=t.deviceId,this.fromData.recyclePosition=t.postCompilt,this.updateStatusPost()},updateStatusPost:function(){var t={id:i()(this.fromData.id),transportStatus:this.fromData.transportStatus,recyclePosition:this.fromData.recyclePosition,deviceNumber:this.fromData.deviceNumber};this.updateTansportStatuspost(t),this.dialogVisible=!1},del:function(t){this.loading=!0;var a={id:i()(t)};this.delPost(a)},listStatus:function(t){if(this.loading=!1,0==t.status){var a="";"up"==t.code&&(a="Chinese"==this.languageData.id?"订单结束成功":t.message),this.openSuccess(a),this.input?this.saechFn():this.saechList()}0!=t.status&&this.openError(t.message),this.$store.commit("updateStatusTo",{body:null})},gomap:function(t){return this.$store.commit("addMapList",null),this.$router.push({name:"map",query:{id:t.id}}),!1},goChart:function(t){this.$router.push({name:"chartSon",query:{id:t.id}})},openSuccess:function(t){this.$message({message:t,type:"success"})},openError:function(t){this.$message.error(t)},copyFn:function(t){new d.a(".copy");this.openSuccess("复制成功")}}),computed:o()({transport:function(){var t=this.transportList;return t&&t.body&&this.listFn(t),1},updateStatusData:function(){var t=this.updateStatus;return t&&this.listStatus(t),1},mapListData:function(){this.mapList;return 1},languageData:function(){var t=this.language;return t&&("Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t}},Object(h.c)(["transportList","updateStatus","language","mapList"])),mounted:function(){this.saechList()},components:{ElButton:g.a,HeaderPublc:r.a}}),x={render:function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",[s("section",{attrs:{id:"header"}},[s("HeaderPublc")],1),t._v(" "),s("section",{attrs:{id:"cent"}},[s("div",{staticClass:"top"}),t._v(" "),s("div",{staticClass:"center"},[s("div",{staticClass:"saech"},[s("input",{directives:[{name:"model",rawName:"v-model",value:t.input,expression:"input"}],staticClass:"saechIput",attrs:{placeholder:t.languageData.public.sechText},domProps:{value:t.input},on:{input:function(a){a.target.composing||(t.input=a.target.value)}}}),t._v(" "),s("el-button",{staticClass:"saechBout",attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(a){t.saechFn()}}})],1),t._v(" "),s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"cardBox clearfix",attrs:{transport:t.transport,"element-loading-text":t.errorDate,"element-loading-spinner":"el-icon-loading","element-loading-background":"#f7f7f7"}},t._l(t.listData,function(a){return s("div",{staticClass:"card clearfix"},[s("div",{staticClass:"span4 device left clearfix",on:{click:function(s){t.goChart(a)}}},[s("div",{staticClass:"left iocnBox"},[s("p",{staticClass:"go"},[t._v(t._s("Chinese"==t.languageData.id?a.transportStatustextC:a.transportStatustextE)+" ")]),t._v(" "),s("div",{staticClass:"iocn"},[s("img",{attrs:{src:a.icon}})])]),t._v(" "),s("div",{staticClass:" left textBox",staticStyle:{position:"relative"}},[s("div",{staticClass:" ctiy "},[t._v(t._s("Chinese"==t.languageData.id?a.startPosition:a.startPositionEn))]),t._v(" "),s("div",{staticClass:" ctiy"},[t._v(t._s("Chinese"==t.languageData.id?a.endPosition:a.endPositionEn))]),t._v(" "),s("div",{staticClass:"text"},[s("p",{staticClass:"goMap",staticStyle:{cursor:"pointer"},on:{click:function(s){s.stopPropagation(),t.gomap(a)}}},[s("i",{staticClass:"el-icon-location"}),t._v(" "+t._s(t.languageData.admin.current)+"："+t._s(a.now)),s("i",{staticClass:"el-icon-arrow-right"})])]),t._v(" "),t._m(0,!0)]),t._v(" "),s("div",{staticClass:"cleae"})]),t._v(" "),s("div",{staticClass:"span3 device left  clearfix"},[s("div",{staticClass:"textBox deviceT",staticStyle:{position:"relative"}},[s("p",[s("label",[t._v(t._s(1==a.transportType?t.languageData.public.plalteB:t.languageData.public.plalte)+"：")]),s("span",[t._v(t._s(a.containerId))]),s("span",{staticClass:"copy",attrs:{"data-clipboard-text":a.containerId},on:{click:function(a){t.copyFn(a)}}},[t._v(" "+t._s(t.languageData.public.copy)+" ")])]),t._v(" "),s("p",{on:{click:function(s){t.goChart(a)}}},[s("label",[t._v(t._s(t.languageData.public.ladingIdBill)+"：")]),s("span",[t._v(t._s(a.ladingId))])]),t._v(" "),s("p",{on:{click:function(s){t.goChart(a)}}},[s("label",[t._v(t._s(t.languageData.public.container)+"：")]),s("span",[t._v(t._s(a.deviceId))])])])]),t._v(" "),s("div",{staticClass:"span3 device left clearfix",attrs:{mapListData:t.mapListData},on:{click:function(s){t.goChart(a)}}},[s("div",{staticClass:"deviceStatus textBox deviceT"},[s("p",[s("label",[t._v(t._s(t.languageData.public.modeOfTransport)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.transportTypeText:a.transportTypeTextEn)+" ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.status)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.transportStatustextC:a.transportStatustextE)+" ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.deviceStatus)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.deviceOnfftext.CN:a.deviceOnfftext.EN)+"  ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.creaedTime)+"： ")]),s("span",[t._v(" "+t._s(a.ymd)+" ")])])])]),t._v(" "),s("div",{staticClass:"cleae"}),t._v(" "),s("div",{staticClass:"timeDate"},[s("p",{staticClass:"year"},[t._v(" "+t._s(a.year?a.year:""))]),t._v(" "),s("p",{directives:[{name:"show",rawName:"v-show",value:a.dateText,expression:"list.dateText"}],staticClass:"data"},[t._v(t._s(a.dateText))]),t._v(" "),s("p",{directives:[{name:"show",rawName:"v-show",value:!a.dateText,expression:"!list.dateText"}],staticClass:"data"},[s("span",[t._v(" "+t._s(a.data))]),t._v(" "),s("i",{staticClass:"month"},[t._v(" "+t._s("Chinese"==t.languageData.id?a.month:a.monthEN)+" "+t._s("Chinese"==t.languageData.id?t.languageData.public.month:""))])])]),t._v(" "),s("div",{staticClass:"set"},[s("div",[s("el-button",{staticClass:"bot",attrs:{round:"",updateStatusData:t.updateStatusData},on:{click:function(s){s.stopPropagation(),t.del(a.id)}}},[t._v(t._s(t.languageData.public.Delete))])],1),t._v(" "),s("div",[s("el-button",{staticClass:"okBot",attrs:{round:""},on:{click:function(s){s.stopPropagation(),t.updatStatusFn(a)}}},[t._v(t._s(t.languageData.public.completion))])],1)])])})),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:!t.errorDate,expression:"!errorDate"}],staticStyle:{"text-align":"right",width:"70%","min-width":"980px","max-width":"1500px","padding-bottom":"40px",margin:"auto"}},[s("el-pagination",{directives:[{name:"show",rawName:"v-show",value:t.pages.cunt>1,expression:"pages.cunt>1"}],attrs:{layout:"prev, pager, next","page-count":t.pages.cunt},on:{"current-change":t.handleCurrentChange}})],1)]),t._v(" "),s("div",{staticClass:"botList"})]),t._v(" "),s("section",{staticStyle:{"text-align":"right"},attrs:{id:"footer"}})])},staticRenderFns:[function(){var t=this.$createElement,a=this._self._c||t;return a("div",{staticClass:"iconMage"},[a("div",{staticClass:"bul"}),this._v(" "),a("div",{staticClass:"solid"}),this._v(" "),a("div",{staticClass:"red"})])}]},P=s("VU/8")(S,x,!1,function(t){s("TFFs")},"data-v-d4ed8cf8",null);a.default=P.exports},TFFs:function(t,a){}});
//# sourceMappingURL=8.6debe68fe4f8cd634fe7.js.map