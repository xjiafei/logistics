webpackJsonp([8],{"1i5Y":function(t,a){},lGr5:function(t,a,e){"use strict";Object.defineProperty(a,"__esModule",{value:!0});var s=e("mvHQ"),i=e.n(s),n=e("Dd8w"),o=e.n(n),l=e("orAT"),r=e("V33R"),d=e.n(r),c=e("AMmF"),u=e("nDrS"),p=e.n(u),v=e("NYxO"),g=e("EMXe"),D=e("Iu90"),N=e.n(D),h=(e("LPE6"),e("+EAe")),m=e.n(h),y=(e("VeD2"),e("xnxj")),_=e.n(y),f=(e("tZ6o"),e("uL0D")),C=e.n(f),S=(e("YUF+"),{name:"my-ChartSon",created:function(){sessionStorage.getItem("user");this.$store.commit("updateDetail",null)},data:function(){return{id:null,year:null,list:[{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2},{id:"AKDFAKJ2",startPosition:{country:"china",city:"Beijing",address:"XXXXX"},endPosition:{country:"china",city:"Beijing",address:"XXXXX"},transportStatus:1,transportType:2}],listData:{id:null,containerId:"No Data",ladingId:"No Data",deviceId:"No Data",deviceOnff:1,deviceOnfftext:{EN:"No Data",CN:"No Data"},startPosition:{country:{EN:"No Data",CN:"No Data"},city:{EN:"No Data",CN:"No Data"},province:{EN:"No Data",CN:"No Data"},address:{EN:"No Data",CN:"No Data"}},endPosition:{country:{EN:"No Data",CN:"No Data"},city:{EN:"No Data",CN:"No Data"},province:{EN:"No Data",CN:"No Data"},address:{EN:"No Data",CN:"No Data"}},startPositionEn:"No Data",transportStatus:0,transportStatusCn:"No Data",transportStatusEn:"No Data",transportType:{EN:"No Data",CN:"No Data"},deviceStatus:0,time:"No Data",year:"No Data",month:"No Data",data:"No Data",dateText:"No Data",transportTypeText:"No Data",transportTypeTextEn:"No Data",completeTime:null,icon:"No Data",now:"No Data",ymd:"No Data",locateFrequency:"No Data",frequencyTypeText:{EN:"No Data",CN:"No Data"}},loading:!1,ladingIdBillShow:!1,dialogVisible:!1,fromData:{id:null,recyclePosition:{country:null,city:null,address:null},transportStatus:1,deviceNumber:null}}},methods:o()({},Object(v.b)({transportListpost:"transportListFn",updateTansportStatuspost:"updateTansportStatus",delPost:"deletefn",checkID:"checkId",mapListFn:"mapListFn",chart:"detail",updateBillon:"updateBillon"}),{saechList:function(t){var a={id:t};this.chart(a)},saechFn:function(){},listFn:function(t){var a,e,s,i;e=(t=t.body.data).createTime?c.a.publicLo.timeCheng(t.createTime):{ymd:null,hms:null,times:null,s:null,d:null,m:null,md:null,week:null,y:null},i=t.completeTime?c.a.publicLo.timeCheng(t.completeTime):{ymd:null,hms:null,times:null,s:null,d:null,m:null,md:null,week:null,y:null};var n=c.a.publicLo.dataChange();if(n.y+n.m==e.y+e.m){var o=parseInt(n.d),l=parseInt(e.d);o==l?s="今天":o-l==1&&(s="昨天")}a=this.ransportSwitch(t.transportType);var r=this.ctiyChart(t.startPosition);t.startPosition.address&&(r.address.CN+=t.startPosition.address,r.address.EN+=t.startPosition.address);var d=this.ctiyChart(t.endPosition);t.endPosition.address&&(d.address.CN+=t.endPosition.address,d.address.EN+=t.endPosition.address);var u={CN:"",EN:""};u={CN:"异常",EN:"error"},t.deviceOnff&&1==t.deviceOnff?u={CN:"开",EN:"ON"}:0==t.deviceOnff&&(u={CN:"关",EN:"OFF"});var p=this.frequencyTypeFn(t.frequencyType);t.newAddress?t.newAddress.length:(t.newAddress="未请求到数据","English"==this.languageData.id&&(t.newAddress="No Data"));var v={id:this.id,containerId:t.containerId,ladingId:t.ladingId,deviceId:t.deviceId,deviceOnff:t.deviceOnff,deviceOnfftext:u,startPosition:r,endPosition:d,transportStatus:t.transportStatus,transportStatusCn:1==t.transportStatus?"运输完成":0==t.transportStatus?"运输中":"异常",transportStatusEn:1==t.transportStatus?"completion":0==t.transportStatus?"transport on":"error",transportType:t.transportType,deviceStatus:0,time:e.hms,year:parseInt(e.y),month:e.m,data:e.d,dateText:s,transportTypeText:a.type,transportTypeTextEn:a.typeEn,icon:a.icon,now:t.newAddress,ymd:e.ymd+" "+e.hms,completeTime:i.ymd+" "+i.hms,locateFrequency:t.locateFrequency,frequencyTypeText:p};this.listData=v},ransportSwitch:function(t){var a;switch(t){case 0:a={type:"火车",icon:C.a,typeEn:"Train"};break;case 1:a={type:"汽车",icon:N.a,typeEn:"Motor"};break;case 2:a={type:"轮船",icon:_.a,typeEn:"Freighter"};break;case 3:a={type:"飞机",icon:m.a,typeEn:"Flight"};break;default:a={type:"异常",icon:N.a}}return a},ctiyChart:function(t){var a,e=p.a[t.country]||{EN:"",CN:""};if(!t.city)return{country:{EN:e.EN,CN:e.CN},city:{EN:"",CN:""},province:{EN:"",CN:""},address:{EN:e.EN+" ",CN:e.CN+" "}};a=t.city.split("_");var s=e[t.country+"_"+a[1]],i=s[a[0]+"_"+a[1]+"_"+a[2]];return{country:{EN:e.EN,CN:e.CN},city:{EN:i.EN,CN:i.CN},province:{EN:s.EN,CN:s.CN},address:{EN:e.EN+" "+s.EN+" "+i.EN+" ",CN:e.CN+" "+s.CN+" "+i.CN+" "}}},frequencyTypeFn:function(t){var a={EN:"",CN:""};return t&&(a.EN=t,"min"==t?a.CN="分钟":"hour"==t?a.CN="小时":"day"==t&&(a.CN="天")),a},updatStatusFn:function(t){var a={id:t,transportStatus:"1"};this.updateTansportStatuspost(a)},del:function(t){var a={id:i()(t)};this.delPost(a)},listStatus:function(t){0==t.status&&(this.openSuccess(t.message),this.input),0!=t.status&&this.openError(t.message),this.$store.commit("updateStatusTo",{body:null})},gomap:function(t){return this.$store.commit("addMapList",null),this.$store.commit("shechDat",t.deviceId),this.$router.push({name:"map",query:{id:t.id}}),!1},goBack:function(){this.$router.go(-1)},goChart:function(t){},updateBill:function(t){if(this.ladingIdBillShow=!this.ladingIdBillShow,t){this.listData.ladingId;var a={id:t,ladingId:this.listData.ladingId};this.updateBillon(a)}},openSuccess:function(t){this.$message({message:t,type:"success"})},openError:function(t){this.$message.error(t)},copyFn:function(t){new d.a(".copy");this.openSuccess("复制成功")}}),computed:o()({updateStatusData:function(){var t=this.updateStatus;return t&&this.listStatus(t),1},DetailData:function(){var t=this.Detail;return t&&this.listFn(t),1},languageData:function(){var t=this.language;return t&&("Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t}},Object(v.c)(["updateStatus","language","Detail"])),mounted:function(){this.$store.commit("updateNave",{logo:!0,login:!1,us:!0,language:!1,user:!0,nav:!0});var t=this.$route.query.id;t&&(this.saechList(t),this.id=t)},components:{ElButton:g.a,HeaderPublc:l.a}}),E={render:function(){var t=this,a=t.$createElement,e=t._self._c||a;return e("div",[e("section",{attrs:{id:"header"}},[e("HeaderPublc")],1),t._v(" "),e("section",{attrs:{id:"cent"}},[e("el-dialog",{attrs:{visible:t.dialogVisible,width:"430px"},on:{"update:visible":function(a){t.dialogVisible=a}}},[e("el-input",{attrs:{placeholder:"请输入回收地址"},model:{value:t.fromData.recyclePosition.address,callback:function(a){t.$set(t.fromData.recyclePosition,"address",a)},expression:"fromData.recyclePosition.address"}}),t._v(" "),e("span",{staticClass:"dialog-footer",staticStyle:{"text-align":"center"},attrs:{slot:"footer"},slot:"footer"},[e("el-button",{staticStyle:{width:"120px"},attrs:{type:"primary",size:"medium",round:""},on:{click:function(a){t.updateStatusPost()}}},[t._v("确 定")]),t._v(" "),e("el-button",{staticStyle:{width:"120px"},attrs:{size:"medium",round:""},on:{click:function(a){t.updatStatusFn(t.listData.id)}}},[t._v("取 消")])],1)],1),t._v(" "),e("div",{staticClass:"top"}),t._v(" "),e("div",{staticClass:"center"},[e("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"cardBox clearfix",attrs:{DetailData:t.DetailData,"element-loading-text":t.languageData.public.loadingText,"element-loading-spinner":"el-icon-loading","element-loading-background":"#f7f7f7"}},[e("div",{staticClass:"goBack",on:{click:function(a){t.goBack()}}},[t._m(0)]),t._v(" "),e("div",{staticClass:"card clearfix",on:{click:function(a){t.goChart(t.listData)}}},[e("div",{staticClass:"span6 device  clearfix"},[e("div",{staticClass:" textBox address clearfix"},[e("div",{staticClass:"left ctiy"},[t._v(t._s("Chinese"==t.languageData.id?t.listData.startPosition.city.CN:t.listData.startPosition.city.EN))]),t._v(" "),e("div",{staticClass:"left iconBox"},[e("div",{staticClass:"iocn"},[e("img",{attrs:{src:t.listData.icon}})]),t._v(" "),e("div",{staticClass:"l"}),t._v(" "),e("p",{staticClass:"go"},[t._v(t._s("Chinese"==t.languageData.id?t.listData.transportStatusCn:t.listData.transportStatusEn))])]),t._v(" "),e("div",{staticClass:"left ctiy"},[t._v(t._s("Chinese"==t.languageData.id?t.listData.endPosition.city.CN:t.listData.endPosition.city.EN))]),t._v(" "),e("div",{staticClass:"cleae"}),t._v(" "),e("div",{staticClass:"text"},[e("p",{staticStyle:{cursor:"pointer"},on:{click:function(a){a.stopPropagation(),t.gomap(t.listData)}}},[e("span",{staticClass:"pt"},[e("i",{staticClass:"el-icon-location"}),t._v(t._s(t.languageData.header.transport)+"："+t._s(t.listData.now)),e("i",{staticClass:"el-icon-arrow-right"})])])])])]),t._v(" "),e("div",{staticClass:"span6 device   clearfix"},[e("div",{staticClass:"textBox deviceT"},[e("p",{staticStyle:{position:"relative"}},[e("label",[t._v(t._s(1==t.listData.transportType?t.languageData.public.plalteB:t.languageData.public.plalte)+"：")]),e("span",[t._v(t._s(t.listData.containerId))]),e("span",{staticClass:"copy",attrs:{"data-clipboard-text":t.listData.containerId},on:{click:function(a){t.copyFn()}}},[t._v(" "+t._s(t.languageData.public.copy)+" ")])]),t._v(" "),e("p",{staticStyle:{position:"relative"}},[e("label",[t._v(t._s(t.languageData.public.ladingIdBill)+"：")]),e("span",{directives:[{name:"show",rawName:"v-show",value:!t.ladingIdBillShow,expression:"!ladingIdBillShow"}]},[t._v(t._s(t.listData.ladingId))]),t._v(" "),e("input",{directives:[{name:"show",rawName:"v-show",value:t.ladingIdBillShow&&1!=t.listData.transportStatus,expression:"ladingIdBillShow && listData.transportStatus!=1"},{name:"model",rawName:"v-model",value:t.listData.ladingId,expression:"listData.ladingId"}],staticClass:"ladingIdBill",attrs:{type:"text"},domProps:{value:t.listData.ladingId},on:{input:function(a){a.target.composing||t.$set(t.listData,"ladingId",a.target.value)}}}),t._v(" "),e("span",{directives:[{name:"show",rawName:"v-show",value:!t.ladingIdBillShow&&1!=t.listData.transportStatus,expression:"!ladingIdBillShow && listData.transportStatus!=1"}],staticClass:"update",on:{click:function(a){t.updateBill()}}},[t._v("修改")]),t._v(" "),e("span",{directives:[{name:"show",rawName:"v-show",value:t.ladingIdBillShow&&1!=t.listData.transportStatus,expression:"ladingIdBillShow && listData.transportStatus!=1"}],staticClass:"update",on:{click:function(a){t.updateBill(t.listData.id)}}},[t._v("确定")])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.container)+"：")]),e("span",[t._v(t._s(t.listData.deviceId))])])])]),t._v(" "),e("div",{staticClass:"span6 device  clearfix"},[e("div",{staticClass:"deviceStatus textBox deviceT"},[e("p",[e("label",[t._v(t._s(t.languageData.public.modeOfTransport)+"： ")]),e("span",[t._v(" "+t._s("Chinese"==t.languageData.id?t.listData.transportTypeText:t.listData.transportTypeTextEn)+" ")])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.status)+"： ")]),e("span",[t._v(" "+t._s("Chinese"==t.languageData.id?t.listData.transportStatusCn:t.listData.transportStatusEn)+"  ")])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.deviceStatus)+"： ")]),e("span",[t._v(" "+t._s("Chinese"==t.languageData.id?t.listData.deviceOnfftext.CN:t.listData.deviceOnfftext.EN)+"  ")])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.creaedTime)+"： ")]),e("span",[t._v(" "+t._s(t.listData.ymd)+" ")])]),t._v(" "),e("p",{directives:[{name:"show",rawName:"v-show",value:"null null"!=t.listData.completeTime,expression:"listData.completeTime != 'null null'"}]},[e("label",[t._v(t._s(t.languageData.public.completionTime)+"： ")]),e("span",[t._v(" "+t._s(t.listData.completeTime)+" ")])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.frequencyGPS)+"： ")]),e("span",[t._v(t._s(t.listData.locateFrequency)+" "+t._s("Chinese"==t.languageData.id?t.listData.frequencyTypeText.CN:t.listData.frequencyTypeText.EN))])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.departure)+"： ")]),e("span",[t._v(t._s("Chinese"==t.languageData.id?t.listData.startPosition.address.CN:t.listData.startPosition.address.EN))])]),t._v(" "),e("p",[e("label",[t._v(t._s(t.languageData.public.desitnation)+"： ")]),e("span",[t._v(t._s("Chinese"==t.languageData.id?t.listData.endPosition.address.CN:t.listData.endPosition.address.EN))])])])]),t._v(" "),e("div",{staticStyle:{"text-align":"right","padding-top":"40px"}},[e("el-button",{staticClass:"bot",attrs:{round:"",updateStatusData:t.updateStatusData},on:{click:function(a){t.del(t.listData.id)}}},[t._v(t._s(t.languageData.public.Delete))]),t._v(" "),e("el-button",{directives:[{name:"show",rawName:"v-show",value:1!=t.listData.transportStatus,expression:"listData.transportStatus!=1"}],staticClass:"okBot",attrs:{round:""},on:{click:function(a){t.dialogVisible=!0}}},[t._v(t._s(t.languageData.public.completion))])],1)])])])],1)])},staticRenderFns:[function(){var t=this.$createElement,a=this._self._c||t;return a("p",{staticClass:"el-icon-arrow-left"},[a("span",{staticStyle:{cursor:"pointer"}},[this._v("返回")])])}]},b=e("VU/8")(S,E,!1,function(t){e("1i5Y")},"data-v-b6ddc08e",null);a.default=b.exports}});
//# sourceMappingURL=8.14e69bb11daabd7eb9df.js.map