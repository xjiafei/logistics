webpackJsonp([9],{StQU:function(t,a,s){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var i=s("mvHQ"),e=s.n(i),n=s("Dd8w"),o=s.n(n),r=s("orAT"),c=s("AMmF"),l=s("V33R"),d=s.n(l),u=s("nDrS"),p=s.n(u),v=s("NYxO"),g=s("EMXe"),h=s("Iu90"),m=s.n(h),f=(s("LPE6"),s("+EAe")),_=s.n(f),y=(s("VeD2"),s("xnxj")),b=s.n(y),C=(s("tZ6o"),s("uL0D")),x=s.n(C),S=(s("YUF+"),{name:"my-Chart",created:function(){var t=sessionStorage.getItem("user");t&&(t=JSON.parse(t),console.log(t),this.user=t)},data:function(){return{dialogVisible:!1,fromData:{id:null,recyclePosition:{country:null,city:null,address:null},transportStatus:1,deviceNumber:null},input:null,year:null,pages:{page:1,pageSize:10,cunt:null},list:[{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2},{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2}],listData:[],loading:!1}},methods:o()({},Object(v.b)({transportListpost:"transportListFn",updateTansportStatuspost:"updateTansportStatus",delPost:"deletefn",checkID:"checkId",mapListFn:"mapListFn"}),{handleCurrentChange:function(t){this.pages.page=parseInt(t),this.input?this.saechFn():this.saechList()},saechList:function(){this.loading=!0;var t={id:1,page:this.pages.page,pageSize:this.pages.pageSize};this.transportListpost(t)},saechFn:function(){if(this.loading=!0,this.input){var t={code:this.input};this.checkID(t)}else{t={id:1};this.transportListpost(t)}},listFn:function(t){if(this.loading=!1,t.body.count){var a=t.body.count;this.pages.cunt=Math.ceil(a/this.pages.pageSize)}t=t.body.data;this.listData=[];var s=[];for(var i in t){var e,n,o;n=t[i].createTime?c.a.publicLo.timeCheng(t[i].createTime):{ymd:null,hms:null,times:null,s:null,d:null,m:null,md:null,week:null,y:null};var r=c.a.publicLo.dataChange();if(r.y+r.m==n.y+n.m){var l=parseInt(r.d),d=parseInt(n.d);l==d?o="今天":l-d==1&&(o="昨天")}e=this.ransportSwitch(t[i].transportType);var u=t[i].startPosition.city.split("_"),v=t[i].endPosition.city.split("_");t[i].startPosition.state=t[i].startPosition.country+"_"+u[1],t[i].endPosition.state=t[i].endPosition.country+"_"+v[1];var g,h=p.a[t[i].startPosition.country][t[i].startPosition.state][t[i].startPosition.city],m=p.a[t[i].endPosition.country][t[i].endPosition.state][t[i].endPosition.city];h||(h={CN:"",EN:""}),m||(m={CN:"",EN:""}),g=t[i].deviceOnff&&1==t[i].deviceOnff?{CN:"开",EN:"ON"}:0==t[i].deviceOnff?{CN:"关",EN:"OFF"}:{CN:"异常",EN:"error"},t[i].newAddress?t[i].newAddress.length>10&&(t[i].newAddress=t[i].newAddress.substring(0,10)+"..."):(t[i].newAddress="未请求到数据","English"==this.languageData.id&&(t[i].newAddress="No Data"));var f={EN:"",CN:""};0==t[i].transportStatus&&(f={EN:"in transit",CN:"运输中"}),1==t[i].transportStatus&&(f={EN:"finished",CN:"运输完成"});(u={id:t[i].id,containerId:t[i].containerId,ladingId:t[i].billNumber,deviceId:t[i].deviceNumber,deviceOnff:t[i].deviceOnff,deviceOnfftext:g,startPosition:h.CN,endPosition:m.CN,startPositionEn:h.EN,endPositionEn:m.EN,transportStatus:t[i].transportStatus,transportStatustextC:f.CN,transportStatustextE:f.EN,transportType:t[i].transportType,deviceStatus:0,time:n.hms,year:parseInt(n.y),month:n.m,data:n.d,dateText:o,transportTypeText:e.type,transportTypeTextEn:e.typeEn,icon:e.icon,now:t[i].newAddress,ymd:n.ymd+" "+n.hms}).startPosition.length>4&&(u.startPosition=u.startPosition.substring(0,4)+"..."),u.endPosition.length>4&&(u.endPosition=u.endPosition.substring(0,4)+"..."),u.startPositionEn.length>8&&(u.startPositionEn=u.startPositionEn.substring(0,8)+"..."),u.endPositionEn.length>8&&(u.endPositionEn=u.endPositionEn.substring(0,8)+"..."),0==i?this.year=u.year:this.year==u.year&&(u.year=""),s.push(u)}this.listData=s},ransportSwitch:function(t){var a;switch(t){case 0:a={type:"火车",icon:x.a,typeEn:"Train"};break;case 1:a={type:"汽车",icon:m.a,typeEn:"Motor"};break;case 3:a={type:"轮船",icon:b.a,typeEn:"Freighter"};break;case 2:a={type:"飞机",icon:_.a,typeEn:"Flight"};break;default:a={type:"异常",icon:m.a}}return a},updatStatusFn:function(t,a){this.dialogVisible=!0,this.fromData.id=t,this.fromData.deviceNumber=a},updateStatusPost:function(){var t={id:e()(this.fromData.id),transportStatus:this.fromData.transportStatus,recyclePosition:this.fromData.recyclePosition,deviceNumber:this.fromData.deviceNumber};this.updateTansportStatuspost(t),this.dialogVisible=!1},del:function(t){this.loading=!0;var a={id:e()(t)};this.delPost(a)},listStatus:function(t){if(this.loading=!1,0==t.status){this.openSuccess("订单结束成功"),this.input?this.saechFn():this.saechList()}0!=t.status&&this.openError(t.message),this.$store.commit("updateStatusTo",{body:null})},gomap:function(t){return this.$store.commit("addMapList",null),this.$router.push({name:"map",query:{id:t.id}}),!1},goChart:function(t){this.$router.push({name:"chartSon",query:{id:t.id}})},openSuccess:function(t){this.$message({message:t,type:"success"})},openError:function(t){this.$message.error(t)},copyFn:function(t){new d.a(".copy");this.openSuccess("复制成功")}}),computed:o()({transport:function(){var t=this.transportList;return t&&t.body&&this.listFn(t),1},updateStatusData:function(){var t=this.updateStatus;return t&&this.listStatus(t),1},mapListData:function(){this.mapList;return 1},languageData:function(){var t=this.language;return t&&("Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t}},Object(v.c)(["transportList","updateStatus","language","mapList"])),mounted:function(){this.saechList()},components:{ElButton:g.a,HeaderPublc:r.a}}),D={render:function(){var t=this,a=t.$createElement,s=t._self._c||a;return s("div",[s("section",{attrs:{id:"header"}},[s("HeaderPublc")],1),t._v(" "),s("section",{attrs:{id:"cent"}},[s("div",{staticClass:"top"}),t._v(" "),s("div",{staticClass:"center"},[s("el-dialog",{attrs:{visible:t.dialogVisible,width:"430px"},on:{"update:visible":function(a){t.dialogVisible=a}}},[s("el-input",{attrs:{placeholder:"请输入回收地址"},model:{value:t.fromData.recyclePosition.address,callback:function(a){t.$set(t.fromData.recyclePosition,"address",a)},expression:"fromData.recyclePosition.address"}}),t._v(" "),s("span",{staticClass:"dialog-footer",staticStyle:{"text-align":"center"},attrs:{slot:"footer"},slot:"footer"},[s("el-button",{staticStyle:{width:"120px"},attrs:{type:"primary",size:"medium",round:""},on:{click:function(a){t.updateStatusPost()}}},[t._v("确 定")]),t._v(" "),s("el-button",{staticStyle:{width:"120px"},attrs:{size:"medium",round:""},on:{click:function(a){t.dialogVisible=!1}}},[t._v("取 消")])],1)],1),t._v(" "),s("div",{staticClass:"saech"},[s("input",{directives:[{name:"model",rawName:"v-model",value:t.input,expression:"input"}],staticClass:"saechIput",attrs:{placeholder:t.languageData.public.sechText},domProps:{value:t.input},on:{input:function(a){a.target.composing||(t.input=a.target.value)}}}),t._v(" "),s("el-button",{staticClass:"saechBout",attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(a){t.saechFn()}}})],1),t._v(" "),s("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"cardBox clearfix",attrs:{transport:t.transport,"element-loading-text":t.languageData.public.loadingText,"element-loading-spinner":"el-icon-loading","element-loading-background":"#f7f7f7"}},t._l(t.listData,function(a){return s("div",{staticClass:"card clearfix"},[s("div",{staticClass:"span4 device left clearfix",on:{click:function(s){t.goChart(a)}}},[s("div",{staticClass:"left iocnBox"},[s("p",{staticClass:"go"},[t._v(t._s("Chinese"==t.languageData.id?a.transportStatustextC:a.transportStatustextE)+" ")]),t._v(" "),s("div",{staticClass:"iocn"},[s("img",{attrs:{src:a.icon}})])]),t._v(" "),s("div",{staticClass:" left textBox",staticStyle:{position:"relative"}},[s("div",{staticClass:" ctiy "},[t._v(t._s("Chinese"==t.languageData.id?a.startPosition:a.startPositionEn))]),t._v(" "),s("div",{staticClass:" ctiy"},[t._v(t._s("Chinese"==t.languageData.id?a.endPosition:a.endPositionEn))]),t._v(" "),s("div",{staticClass:"text"},[s("p",{staticClass:"goMap",staticStyle:{cursor:"pointer"},on:{click:function(s){s.stopPropagation(),t.gomap(a)}}},[s("i",{staticClass:"el-icon-location"}),t._v(" "+t._s(t.languageData.header.transport)+"："+t._s(a.now)),s("i",{staticClass:"el-icon-arrow-right"})])]),t._v(" "),t._m(0,!0)]),t._v(" "),s("div",{staticClass:"cleae"})]),t._v(" "),s("div",{staticClass:"span3 device left  clearfix"},[s("div",{staticClass:"textBox deviceT",staticStyle:{position:"relative"}},[s("p",[s("label",[t._v(t._s(1==a.transportType?t.languageData.public.plalteB:t.languageData.public.plalte)+"：")]),s("span",[t._v(t._s(a.containerId))]),s("span",{staticClass:"copy",attrs:{"data-clipboard-text":a.containerId},on:{click:function(a){t.copyFn(a)}}},[t._v(" "+t._s(t.languageData.public.copy)+" ")])]),t._v(" "),s("p",{on:{click:function(s){t.goChart(a)}}},[s("label",[t._v(t._s(t.languageData.public.ladingIdBill)+"：")]),s("span",[t._v(t._s(a.ladingId))])]),t._v(" "),s("p",{on:{click:function(s){t.goChart(a)}}},[s("label",[t._v(t._s(t.languageData.public.container)+"：")]),s("span",[t._v(t._s(a.deviceId))])])])]),t._v(" "),s("div",{staticClass:"span3 device left clearfix",attrs:{mapListData:t.mapListData},on:{click:function(s){t.goChart(a)}}},[s("div",{staticClass:"deviceStatus textBox deviceT"},[s("p",[s("label",[t._v(t._s(t.languageData.public.modeOfTransport)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.transportTypeText:a.transportTypeTextEn)+" ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.status)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.transportStatustextC:a.transportStatustextE)+" ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.deviceStatus)+"： ")]),s("span",[t._v(" "+t._s("Chinese"==t.languageData.id?a.deviceOnfftext.CN:a.deviceOnfftext.EN)+"  ")])]),t._v(" "),s("p",[s("label",[t._v(t._s(t.languageData.public.creaedTime)+"： ")]),s("span",[t._v(" "+t._s(a.ymd)+" ")])])])]),t._v(" "),s("div",{staticClass:"cleae"}),t._v(" "),s("div",{staticClass:"timeDate"},[s("p",{staticClass:"year"},[t._v(" "+t._s(a.year?a.year:""))]),t._v(" "),s("p",{directives:[{name:"show",rawName:"v-show",value:a.dateText,expression:"list.dateText"}],staticClass:"data"},[t._v(t._s(a.dateText))]),t._v(" "),s("p",{directives:[{name:"show",rawName:"v-show",value:!a.dateText,expression:"!list.dateText"}],staticClass:"data"},[s("span",[t._v(" "+t._s(a.data))]),t._v(" "),s("i",{staticClass:"month"},[t._v(" "+t._s(parseInt(a.month))+" "+t._s(t.languageData.public.month))])])]),t._v(" "),s("div",{staticClass:"set"},[s("div",[s("el-button",{staticClass:"bot",attrs:{round:"",updateStatusData:t.updateStatusData},on:{click:function(s){s.stopPropagation(),t.del(a.id)}}},[t._v(t._s(t.languageData.public.Delete))])],1),t._v(" "),s("div",[s("el-button",{staticClass:"okBot",attrs:{round:""},on:{click:function(s){s.stopPropagation(),t.updatStatusFn(a.id,a.deviceId)}}},[t._v(t._s(t.languageData.public.completion))])],1)])])})),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:!t.loading,expression:"!loading"}],staticStyle:{"text-align":"right",width:"70%","min-width":"980px","max-width":"1500px","padding-bottom":"40px",margin:"auto"}},[s("el-pagination",{directives:[{name:"show",rawName:"v-show",value:t.pages.cunt>1,expression:"pages.cunt>1"}],attrs:{layout:"prev, pager, next","page-count":t.pages.cunt},on:{"current-change":t.handleCurrentChange}})],1)],1),t._v(" "),s("div",{staticClass:"botList"})]),t._v(" "),s("section",{staticStyle:{"text-align":"right"},attrs:{id:"footer"}})])},staticRenderFns:[function(){var t=this.$createElement,a=this._self._c||t;return a("div",{staticClass:"iconMage"},[a("div",{staticClass:"bul"}),this._v(" "),a("div",{staticClass:"solid"}),this._v(" "),a("div",{staticClass:"red"})])}]},P=s("VU/8")(S,D,!1,function(t){s("bRlw")},"data-v-7f0dbdd8",null);a.default=P.exports},bRlw:function(t,a){}});
//# sourceMappingURL=9.978af03a1e785c17c00f.js.map