webpackJsonp([1],{"6SCo":function(t,e){},a2Yd:function(t,e){},k2bH:function(t,e,s){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=s("mvHQ"),i=s.n(a),n=s("Dd8w"),l=s.n(n),o=s("orAT"),c=s("NYxO"),r=s("AMmF"),u=s("yKu/"),h=s.n(u),p={name:"index",created:function(){this.check=this.$route.name;var t=r.a.publicLo.sessionStorageGet("user");t&&(t=JSON.parse(t),this.user={nickName:t.nickName,headImg:t.headImg,id:t.userId})},data:function(){return{boll:!1,showBox:!1,offset:-350,platform:null,behavior:null,defaultLayers:null,ui:null,list:[],user:{nickName:null,headImg:null,id:null},bubble:null,routeInstructionsContainer:null,input:null,logoImg:h.a}},methods:l()({},Object(c.b)({mapListFn:"mapListFn",loginFn:"loginFn",checkID:"checkId",checkNumber:"checkNumber"}),{goMap:function(t){var e={id:t};this.mapListFn(e),this.$store.commit("shechDat",this.input),this.$router.push({name:"map",query:{id:t}})},sechlistFn:function(){this.showBox=!this.showBox;var t=this.input;if(t){if(!(t=t.replace(/(^\s*)|(\s*$)/g," ")))return!1;var e={code:this.input};this.$store.commit("transportListTo",null),this.user.id?this.checkNumber(e):this.checkID(e)}}}),computed:l()({checkIdList:function(){var t=this.shechIput,e=null;return t&&(e=t,this.firstSech=!1,this.listFn()),e},languageData:function(){var t=this.language;return t&&("Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t},transport:function(){var t=this.transportList||null;return t&&(t=t.body.data),t}},Object(c.c)(["shechList","language","transportList"])),mounted:function(){},components:{}},d={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("section",{attrs:{id:"cent"}},[s("div",{staticClass:"box"},[s("div",{staticClass:"center"},[s("div",{staticClass:"logoText logo"},[s("img",{staticStyle:{height:"100px"},attrs:{src:t.logoImg}})]),t._v(" "),s("p",{staticClass:"logoText"},[t._v("运踪宝")]),t._v(" "),s("div",{staticClass:"saech"},[s("el-input",{staticClass:"saechIput",attrs:{placeholder:t.languageData.public.sechText},model:{value:t.input,callback:function(e){t.input=e},expression:"input"}}),t._v(" "),s("el-button",{staticClass:"saechBout",attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){t.sechlistFn()}}}),t._v(" "),s("div",[s("el-collapse-transition",[s("div",{directives:[{name:"show",rawName:"v-show",value:t.showBox,expression:"showBox"}],staticClass:"listBox"},t._l(t.transport,function(e){return s("div",{staticClass:"list",staticStyle:{"text-align":"left","border-bottom":"1px solid #e1e1e1"},on:{click:function(s){t.goMap(e.id)}}},[s("span",{staticStyle:{margin:"0px 20px"}},[t._v(t._s(t.languageData.public.plalte)+"："+t._s(e.containerId))]),t._v(" "),s("span",[t._v(t._s(t.languageData.public.container)+"："+t._s(e.deviceNumber))])])}))])],1)],1)])])])])},staticRenderFns:[]},m=s("VU/8")(p,d,!1,function(t){s("a2Yd")},"data-v-4c970b6f",null).exports,v=s("V33R"),g=s.n(v),f=s("zLv/"),b=s.n(f),C={name:"my-map",created:function(){this.check=this.$route.name;var t=r.a.publicLo.sessionStorageGet("user");t&&(t=JSON.parse(t),this.user={nickName:t.nickName,headImg:t.headImg,id:t.userId})},data:function(){return{tableData:[],makImg:b.a,mapDate:null,platform:null,behavior:null,defaultLayers:null,ui:null,list2:[],ULlist:{first:{lat:null,lng:null,ymd:null,hms:null,location:null},list:[],last:{lat:null,lng:null,ymd:null,hms:null,location:null},listCnut:null},dataChart:{containerId:null,transportStatus:null,transportStatustext:{EN:"",CN:""},deviceOnffText:{EN:"",CN:""},deviceNumber:null,startTime:null},bubble:null,routeInstructionsContainer:null,input:null,firstSech:!0,clickText:"共10个位置",clickStatus:!1,showBox:!1,user:{nickName:null,headImg:null,id:null}}},methods:l()({},Object(c.b)({mapListpost:"mapListFn",checkID:"checkId",checkNumber:"checkNumber"}),{mapFn:function(t,e){e?t.setCenter({lat:e.lat,lng:e.lng}):t.setCenter({lat:52.516,lng:13.3779}),t.setZoom(13),t.setBaseLayer(this.defaultLayers.normal.traffic)},mapService:function(){var t=document.getElementById("map");this.platform=new H.service.Platform({app_id:"X6Xrn8u6EXOVQmSMPlIP",app_code:"_z8PSLTydAFciEMziLSSlg"}),this.defaultLayers=this.platform.createDefaultLayers(),this.map=new H.Map(t,this.defaultLayers.normal.map),this.behavior=new H.mapevents.Behavior(new H.mapevents.MapEvents(this.map)),this.ui=H.ui.UI.createDefault(this.map,this.defaultLayers),this.mapFn(this.map)},cityFn:function(){this.$http.get("/api/cityList",{headers:{}}).then(function(t){})},calculateRouteFromAtoB:function(t){t.getRoutingService().calculateRoute({mode:"fastest;car",representation:"display",routeattributes:"waypoints,summary,shape,legs",maneuverattributes:"direction,action",waypoint0:"52.5160,13.3779",waypoint1:"52.5206,13.3862"},this.onSuccess,this.onError)},addRouteShapeToMap:function(t){var e,s=new H.geo.LineString;t.forEach(function(t){s.pushLatLngAlt(t.lat,t.lng)}),e=new H.map.Polyline(s,{style:{lineWidth:4,strokeColor:"rgba(0, 128, 255, 0.7)"}}),this.map.addObject(e),this.map.setViewBounds(e.getBounds(),!0)},addManueversToMap:function(t){var e,s=this,a=new H.map.Group;for(var i in t){e=0==i?new H.map.Icon("../../../static/img/icon/stra.png",{anchor:{x:13,y:11}}):i==t.length-1&&0!=i?new H.map.Icon("../../../static/img/icon/end.png",{anchor:{x:15,y:25}}):new H.map.Icon('<svg width="18" height="18" xmlns="http://www.w3.org/2000/svg"><circle cx="8" cy="8" r="8" fill="#0077ff" stroke="white" stroke-width="1"  /></svg>',{anchor:{x:8,y:8}});var n=new H.map.Marker({lat:t[i].lat,lng:t[i].lng},{icon:e});n.instruction='<p style="font-size: 14px;text-align: left">当前:'+t[i].location+"</p>",n.instruction+='<p style="font-size: 12px;text-align: left">更新时间:'+t[i].ymd+t[i].hms+"</p>",a.addObject(n),i==t.length-1&&this.openBubble({lat:t[i].lat,lng:t[i].lng},n.instruction)}a.addEventListener("tap",function(t){s.map.setCenter(t.target.getPosition()),s.openBubble(t.target.getPosition(),t.target.instruction)},!1),this.map.addObject(a)},onSuccess:function(t){var e=t.response.route[0];this.addRouteShapeToMap(e),this.addManueversToMap(e)},onError:function(){alert("Ooops!")},openBubble:function(t,e){this.bubble?(this.bubble.setPosition(t),this.bubble.setContent(e),this.bubble.open()):(this.bubble=new H.ui.InfoBubble(t,{content:e,style:"color:#999"}),this.ui.addBubble(this.bubble))},saechFn:function(){this.showBox=!this.showBox;var t=this.input;if(!(t=t.replace(/(^\s*)|(\s*$)/g,"")))return!1;var e={code:this.input};this.$store.commit("transportListTo",null),this.user.id?this.checkNumber(e):this.checkID(e)},listFn:function(t){var e={id:t};this.showBox=!this.showBox,this.$store.commit("addMapList",null),this.mapListpost(e)},opentList:function(){var t=(a=document.getElementById("ul")).scrollHeight,e=document.getElementById("sty"),s="";if(!this.clickStatus)return t>300&&(a.style="height：300px;overflow-y:scroll",t=300),s+="#bodyBox #ul{ height:0px; animation: myA 1s forwards;  } @keyframes myA {0%{height: 0px;} 100%{height: "+t+"px;}",e.innerHTML=s,this.clickStatus=!0,"Chinese"==this.languageCheak&&(this.clickText="点击收起"),"English"==this.languageCheak&&(this.clickText="Fold"),!1;var a=document.getElementById("ul");if(s+="",(e=document.getElementById("sty")).innerHTML=s,this.clickStatus=!1,"Chinese"==this.languageCheak){var i=this.ULlist.listCnut-2;this.clickText="共 "+i+" 个位置"}if("English"==this.languageCheak){i=this.ULlist.listCnut-2;this.clickText=i+" tracking event"}},mapListDate:function(t){var e=[],s={ymd:"",hms:""};(t=t.body.data).startTime&&(s=r.a.publicLo.timeCheng(t.startTime));var a={EN:"",CN:""};0==t.transportStatus&&(a={EN:"in transi",CN:"运输中"}),1==t.transportStatus&&(a={EN:"finished",CN:"运输完成"});var i="";1==t.deviceOnff&&(i={EN:"ON",CN:"开机"}),0==t.deviceOnff&&(i={EN:"OFF",CN:"关机"});var n={containerId:t.containerId,transportStatus:t.transportStatus,transportStatustext:a,deviceOnffText:i,transportType:t.transportType,deviceNumber:t.deviceNumber,startTime:s.ymd+s.hms};console.log(n),this.dataChart=n;var l={first:null,list:[],last:null,listCnut:null},o=(t=t.tracks).length;if(t.length>0){for(var c in t){var u=r.a.publicLo.timeCheng(t[c].time);t[c].location||("Chinese"==this.languageCheak&&(t[c].location="No Data"),"English"==this.languageCheak&&(t[c].location="No Data"));var h={containerId:n.containerId,transportStatus:n.transportStatus,deviceNumber:n.deviceNumber,lat:t[c].latitude,lng:t[c].longitude,ymd:u.ymd,hms:u.hms,time:u.ymd+" "+u.hms,location:t[c].location};this.tableData.push(h),0==c&&(l.first=h,l.last={lat:null,lng:null,ymd:null,hms:null,location:null},this.map&&this.mapFn(this.map,h)),c>=o-1&&o>1&&(l.last=h,l.listCnut=o),c>0&&c<o-1&&l.list.push(h),e.push(h)}this.ULlist=l,e.length>1&&this.addRouteShapeToMap(e),this.addManueversToMap(e)}if("Chinese"==this.languageCheak){var p=this.ULlist.listCnut-2;this.clickText="共 "+p+" 个位置"}if("English"==this.languageCheak){p=this.ULlist.listCnut-2;this.clickText=p+" tracking event"}},updateMap:function(t){var e='<p style="font-size: 14px;text-align: left">当前:'+t.location+"</p>";e+='<p style="font-size: 12px;text-align: left">更新时间:'+t.ymd+t.hms+"</p>",this.map.setCenter({lat:t.lat,lng:t.lng}),this.openBubble({lat:t.lat,lng:t.lng},e)},copyFn:function(t){new g.a(".bot");this.openSuccess("复制成功")},openSuccess:function(t){this.$message({message:t,type:"success"})},openError:function(t){this.$message.error(t)},export2Excel:function(){var t=this;if(this.tableData.length<=0)return!1;s.e(13).then(function(){var e=s("zWO4").export_json_to_excel;console.log(t.languageData,"languageData");var a=["设备号","柜号（车牌号）","创建时间","纬度","经度","地址"];"English"==t.languageData.id&&(a=["Container No","Plate No","Created on","Latitude","longitude","address"]);var i=t.tableData;e(a,t.formatJson(["deviceNumber","containerId","time","lat","lng","location"],i),"deviceList")}.bind(null,s)).catch(s.oe)},formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return e[t]})})}}),computed:l()({deviceId:function(){var t=this.shechIput,e=null;return t&&(e=t,this.firstSech=!1,this.input=t),e},languageData:function(){var t=this.language;return t&&(this.languageCheak=t.check,"Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t},mapLi:function(){var t=this.mapList;if(t){if(i()(this.list2)!=i()(t)){this.list2=t;i()(this.fisrtList)!=i()(t.body)&&(this.mapDate?this.mapListDate(t):this.id||this.mapDate?this.id&&!this.mapDate&&(this.mapDate=t,this.mapListDate(t)):this.input?this.mapListDate(t):this.mapDate=t,this.fisrtList=t.body)}}return 1},transport:function(){var t=this.transportList||null;return t&&(t=t.body.data),t}},Object(c.c)(["shechIput","language","mapList","transportList"])),mounted:function(){var t=document.documentElement.clientHeight;if(t-=52,document.getElementById("map").style.height=t+"px",this.mapService(),this.id=this.$route.query.id,this.id){var e={id:this.id};this.firstSech=!1,this.mapListpost(e),this.mapDate&&this.mapListDate(this.mapDate)}},components:{HeaderPublc:o.a,saechFirst:m}},x={render:function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("section",{attrs:{id:"bodyBox"}},[s("section",{staticClass:"header"},[s("HeaderPublc")],1),t._v(" "),s("section",{staticClass:"cent"},[s("div",{attrs:{id:"map"}}),t._v(" "),s("div",{attrs:{id:"plan"}},[s("div",{directives:[{name:"show",rawName:"v-show",value:!t.firstSech,expression:"!firstSech"}],staticClass:"saech",attrs:{deviceId:t.deviceId}},[s("el-input",{staticClass:"saechIput",attrs:{placeholder:t.languageData.public.sechText},model:{value:t.input,callback:function(e){t.input=e},expression:"input"}}),t._v(" "),s("el-button",{staticClass:"saechBout",attrs:{type:"primary",icon:"el-icon-search"},on:{click:function(e){t.saechFn()}}}),t._v(" "),s("div",[s("el-collapse-transition",{attrs:{height:"200"}},[s("div",{directives:[{name:"show",rawName:"v-show",value:t.showBox,expression:"showBox"}],staticClass:"listBox"},t._l(t.transport,function(e){return s("div",{staticClass:"list",staticStyle:{"text-align":"left","border-bottom":"1px solid #e1e1e1"},on:{click:function(s){t.listFn(e.id)}}},[s("span",{staticStyle:{margin:"0px 20px"}},[t._v(t._s(t.languageData.public.plalte)+"："+t._s(e.containerId))]),t._v(" "),s("span",[t._v(t._s(t.languageData.public.container)+"："+t._s(e.deviceNumber))])])}))])],1)],1),t._v(" "),s("section",{directives:[{name:"show",rawName:"v-show",value:!t.firstSech,expression:"!firstSech"}],attrs:{id:"list"}},[s("div",{staticClass:"listTop"},[s("p",[s("label",[t._v(" "+t._s(t.languageData.public.container)+"：")]),t._v(" "),s("span",{attrs:{id:"copyText"}},[t._v(t._s(t.dataChart.deviceNumber))])]),t._v(" "),s("p",[s("label",[t._v(" "+t._s(1==t.dataChart.transportType?t.languageData.public.plalteB:t.languageData.public.plalte)+"：")]),t._v(" "),s("span",[t._v(t._s(t.dataChart.containerId))])]),t._v(" "),s("p",[s("label",[t._v(" "+t._s(t.languageData.public.status)+"：")]),t._v(" "),s("span",{staticClass:"status"},[t._v(t._s("Chinese"==t.languageData.id?t.dataChart.transportStatustext.CN:t.dataChart.transportStatustext.EN))])]),t._v(" "),s("p",[s("label",[t._v(" "+t._s(t.languageData.public.deviceStatus)+"：")]),t._v(" "),s("span",{staticClass:"status"},[t._v(t._s("Chinese"==t.languageData.id?t.dataChart.deviceOnffText.CN:t.dataChart.deviceOnffText.EN))])]),t._v(" "),s("p",[s("label",[t._v(" "+t._s(t.languageData.public.creaedTime)+"：")]),t._v(" "),s("span"),t._v(t._s(t.dataChart.startTime))]),t._v(" "),s("span",{staticClass:"bot",attrs:{"data-clipboard-text":t.dataChart.deviceNumber},on:{click:function(e){t.copyFn(11)}}},[t._v(" "+t._s(t.languageData.public.copy)+" ")])]),t._v(" "),s("div",{staticClass:"listCent"},[s("div",{directives:[{name:"show",rawName:"v-show",value:t.ULlist.first.location,expression:"ULlist.first.location"}],staticClass:"listBoder"},[s("div",{staticClass:"first padding",on:{click:function(e){t.updateMap(t.ULlist.first)}}},[s("p",[t._v(t._s(t.ULlist.first.location))]),s("i",{staticClass:"time",staticStyle:{right:"25px"}},[t._v(t._s(t.ULlist.first.ymd)+" "+t._s(t.ULlist.first.hms))]),t._v(" "),t._m(0)]),t._v(" "),s("div",{staticClass:"aimintUl ",attrs:{mapLi:t.mapLi}},[s("ul",{directives:[{name:"show",rawName:"v-show",value:t.ULlist.list.length>0,expression:"ULlist.list.length>0"}],staticClass:"padding",attrs:{id:"ul"}},t._l(t.ULlist.list,function(e){return s("li",{on:{click:function(s){t.updateMap(e)}}},[s("p",[t._v(t._s(e.location))]),s("i",{staticClass:"time"},[t._v(t._s(e.ymd)+" "+t._s(e.hms))]),t._v(" "),s("div",{staticClass:"ricM"})])}))]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.ULlist.listCnut,expression:"ULlist.listCnut"}],staticClass:"next padding"},[s("span",{staticStyle:{"margin-left":"5px"}},[t._v(t._s(t.clickText))]),t._v(" "),s("span",{class:{"el-icon-arrow-up":t.clickStatus,"el-icon-arrow-down":!t.clickStatus},on:{click:function(e){t.opentList()}}})]),t._v(" "),s("div",{directives:[{name:"show",rawName:"v-show",value:t.ULlist.last.location,expression:"ULlist.last.location"}],staticClass:"last padding",on:{click:function(e){t.updateMap(t.ULlist.last)}}},[s("p",{},[t._v(t._s(t.ULlist.last.location))]),t._v(" "),s("i",{staticClass:"time",staticStyle:{right:"25px"}},[t._v(t._s(t.ULlist.last.ymd)+" "+t._s(t.ULlist.last.hms))]),t._v(" "),t._m(1)])]),t._v(" "),s("p",{directives:[{name:"show",rawName:"v-show",value:!t.ULlist.first.location,expression:"!ULlist.first.location"}]},[t._v("暂无数据")])]),t._v(" "),s("div",{staticClass:"listFooter"},[s("el-button",{attrs:{round:""},on:{click:function(e){t.export2Excel()}}},[t._v(t._s(t.languageData.public.exportCSV))])],1)]),t._v(" "),s("section",{directives:[{name:"show",rawName:"v-show",value:t.firstSech,expression:"firstSech"}],attrs:{id:"saechFirst"}},[s("saechFirst")],1)])]),t._v(" "),s("section",{staticClass:"footer"})])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"ricBoder"},[e("div",{staticClass:"ric"})])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"ricBoder"},[e("div",{staticClass:"ric"})])}]},y=s("VU/8")(C,x,!1,function(t){s("6SCo")},"data-v-41c69029",null);e.default=y.exports},"yKu/":function(t,e,s){t.exports=s.p+"static/img/logo180.2433c53.png"},"zLv/":function(t,e){t.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAYAAADFw8lbAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABB5JREFUeNrMmX9M1GUcx993HIoOEDgPZivSgTBygYrGjhUFzBoW/WGwZZvOijmm1iazWm7VP3Bu5UJgKYLaMpWKVZtu4bTF0s1gqYG5oQfMH1s/SI4DTiDhxvV5jg90h/fjueP7Pe6zvXa37/d5Pp/3vs/z/Tyf5/lq7tgcCNIWEEYin8gk0ohlRDTfv0/8RZiJa0Qr8QsxHkwwTRBC1xDlRAmREGBfK9FM1BO/qSU0izARRaIf5mYi6FnifaJTpoNWok0U8SlxmdiogEiwjyL2Wc0x5vREU3moVkNd6yBKiZ5gnuh64lIIRIJjtBE5gQoVHX4iDAid6YkfvYnVehnuMy5pJpQWzbFT/QmN4jlpwPyZgTVE+RJqCtGclJmzVd7e+ixOFzqEh9mJbF7V3J6oKYxEgrWYZg/9Gk7A4WYbWduM0J0KrThKm4a1OefoQvrtI5b46rG1uAD3bcPSEZ5/eRPKK/YqIXaISNJygl3ir3UgIoWNjY4q9VSFthwxYQvUGjerpR9dv3e4XVu0eDEys3MQERERiKsCHaclVayx5mO0X2zFipXpNNk0VNs5cLvHjJT0DFTWNiIycoF0ianjylwVG7IO4JHHHsf+hhMz1y6cb0GN6UOcbPwM23bslnWVJuZokqrJUOeemvM2FOGlks0403wKN65fk3WTJLzEqCnUOmDBlw11btcmJyfhcDhwuHofqo82ybiJUX0lGh604vtTX3i8d6e3W9qPGHqbVObVajGPZtNxstf7a7ll+y7YhoekPa/KXItvT36ulNA+He+7n/DX8nh97f/75ady8UzhC7Db7fjhu69xu9f8UHvLvX/ccmfp1jIk6A3oudlFfb5yztMAzKyV3a5qNFOlQMaTq50iD+6vRNOxQyjfsxfRsbEP74fpZZlO6m++/Q76+/7GgaoP0N11HW+8tcd5fdmjybJCO8Van0d/fvbX8u6tXkxMjEO/NBG1+z5Cx69tzuuv76zAOuPTGB0dcWsfF6/HoNWCvj//wPrcPGwpzsf4gwfOe58cPo7hoUGkpGUgNi5eRuhzYujbeeH3ud4nr0iZ+f/iK6/ScHcjPkEP47OFWJroORXrDYlOMcI2vbYNp7854Vw+l6ekIUInnXBEkdE2XeEfESOE8LSjRNl0zjmI8LVDroXzVeJcGIoUmq7M3jO9xxuqcDGh5V1P22VRONaFkdA619Q5+5BsUQjPm3zmTUwdEo95O4AY41O1e/MoUsQucRXp7exJHP0VEyPzIHKEY/fIHJKBF4FCsWSHUKSFY7Z7K/PgQ6yRXzK1TcTI9SbSn1Bh3Sz2gEqpy86+jVzFIVihwv4lxC5sHdGioMgW9rmbY2CuQl1ThjgLWks0EAPBbKG4bzb76pTtqFHwg1g6PH8QuwkFPoj9J8AAzsEjZY9n2LEAAAAASUVORK5CYII="}});
//# sourceMappingURL=1.f35d38a0ab83ec6f03e5.js.map