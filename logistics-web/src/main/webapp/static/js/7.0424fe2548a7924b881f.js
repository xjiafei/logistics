webpackJsonp([7],{"77zf":function(t,i){},CDzc:function(t,i){},DeDE:function(t,i,e){"use strict";Object.defineProperty(i,"__esModule",{value:!0});var n=e("jbb4"),a=e("mvHQ"),o=e.n(a),s=e("pFYg"),r=e.n(s),c=e("Dd8w"),l=e.n(c),u=e("qiyf"),d=e.n(u),y=e("nDrS"),p=e.n(y),v=e("NYxO"),h={name:"my-cityList",created:function(){var t=sessionStorage.getItem("user");t&&(t=JSON.parse(t),console.log(t),this.user=t)},data:function(){var t=this;return{loading:!1,submintA:!0,labelPosition:"left",user:{imgUlr:d.a,name:"小明管理员"},fromData:{country:null,province:null,city:null},rules:{country:[{required:!0,message:"请输入用户商家名",trigger:"blur"},{min:3,max:20,message:"长度在 3 到 20 个字符",trigger:"blur"}],province:[{required:!0,message:"请输入登陆账号",trigger:"blur"},{min:3,max:20,message:"长度在 3 到 20 个字符",trigger:"blur"}],city:[{validator:function(i,e,n){if(!e)return n(new Error("请选择城市"));setTimeout(function(){if(t.getlist.data.indexOf(t.fromData.city)>=0)return n(new Error("该城市已有"));n()},300)},trigger:"change"}]},left:"left",getlist:{data:[]},options:[{code:"1",CN:"",EN:""}],provincePtions:[{code:"",CN:"",EN:""}],cityPtion:[{code:"",CN:"",EN:""}],provinceCheck:!0,countryData:[{country:{id:null,CN:null,EN:null,province:[{id:null,CN:null,EN:null,city:[{id:null,CN:null,EN:null}]}]}}]}},methods:l()({},Object(v.b)({setCityPost:"setCityList",getCityList:"getCityList"}),{tableRowClassName:function(t){t.row;var i=t.rowIndex%2;return 0===i?"":1===i?"success-row":""},handleCurrentChange:function(t){console.log("当前页: "+t)},login:function(){this.$router.push({name:"map"})},submitForm:function(t){var i=this;this.$refs[t].validate(function(t){if(!t)return console.log("error submit!!"),!1;i.submintA=!1,i.getlist.data.push(i.fromData.city),i.cityListFn(i.getlist),i.fromData.country="",i.fromData.province="",i.fromData.city="",i.cityPtion=[{code:"",CN:"",EN:""}],i.provincePtions=[{code:"",CN:"",EN:""}]})},cityListFn:function(t){this.getlist=t,t=t.data;var i=[];for(var e in t){var n=p.a,a={country:{CN:"",id:"",EN:""},province:{CN:"",id:"",EN:""},city:{CN:"",id:"",EN:""}},o=t[e].split("_");a.country.id=o[0],a.country.CN=n[a.country.id].CN,a.country.EN=n[a.country.id].EN,o&&(a.province.id=a.country.id+"_"+o[1],a.province.CN=n[a.country.id][a.province.id].CN,a.province.EN=n[a.country.id][a.province.id].EN,a.city.id=a.province.id+"_"+o[2],a.city.CN=n[a.country.id][a.province.id][a.city.id].CN,a.city.EN=n[a.country.id][a.province.id][a.city.id].EN),i.push(a)}for(var s=[],r=[],c={},l=0;l<i.length;l++){var u=i[l].country.id;c[u]?r.push({pid:i[l].country.id,id:i[l].province.id,CN:i[l].province.CN,EN:i[l].province.EN,city:[{id:i[l].city.id,CN:i[l].city.CN,EN:i[l].city.EN}]}):(s.push({country:{id:i[l].country.id,CN:i[l].country.CN,EN:i[l].country.EN,province:[{id:i[l].province.id,CN:i[l].province.CN,EN:i[l].province.EN,city:[{id:i[l].city.id,CN:i[l].city.CN,EN:i[l].city.EN}]}]}}),c[u]=!0)}for(var d in r)for(var y in s){var v=!0;if(r[d].pid==s[y].country.id){for(var h in s[y].country.province)if(r[d].id==s[y].country.province[h].id){var g=s[y].country.province[h].city,m=r[d].city,f=g.concat(m);v=!1,s[y].country.province[h].city=f}v&&s[y].country.province.push(r[d])}}this.countryData=s},cityChange:function(t){if("country"==t){var i=p.a[this.fromData.country];this.provincePtions=this.citPotin(i,"country"),this.fromData.province="",this.fromData.city="",this.provincePtions[0].check?(this.provinceCheck=!1,this.fromData.province=this.provincePtions[0].code,t="province"):(this.provinceCheck=!0,t="country")}if("province"==t){i=p.a[this.fromData.country][this.fromData.province];this.cityPtion=this.citPotin(i),this.fromData.city=""}},citPotin:function(t,i){var e=[],n={code:null,EN:null,CN:null};for(var a in t)"country"?t[a].EN?(n={code:a,EN:t[a].EN,CN:t[a].CN},e.push(n)):"object"==r()(t[a])&&(n={code:a,EN:t[a].EN||"Municipality",CN:t[a].CN||"直辖市",check:!0},e.push(n)):t[a].EN&&(n={code:a,EN:t[a].EN,CN:t[a].CN},e.push(n));return e},updateCity:function(){var t=sessionStorage.getItem("user");t=o()(t);var i={companyId:this.user.companyId,cityList:this.getlist.data};this.loading=!0,this.setCityPost(i)},delCityFn:function(t){var i=this.getlist.data;this.submintA=!1,1==i.length&&(this.submintA=!0);for(var e in i)if(t==i[e])return i.splice(e,1),void this.cityListFn(this.getlist.data)},listStatus:function(t){if(this.loading=!1,0==t.status){var i="Chinese"==this.languageData.id?"城市列表提交成功":"City list submission success";this.openSuccess(i);var e={company:this.user.companyName};this.getCityList(e),this.options=this.citPotin(p.a)}0!=t.status&&this.openError(t.message),this.$store.commit("updateStatusTo",{body:null})},openSuccess:function(t){this.$message({message:t,type:"success"})},openError:function(t){this.$message.error(t)}}),computed:l()({cityList:function(){var t=this.cityListData;return t&&this.cityListFn(t.body),1},updateStatusData:function(){var t=this.updateStatus;return t&&this.listStatus(t),1},languageData:function(){var t=this.language;return t&&("Chinese"==t.check&&(t=t.Chinese),"English"==t.check&&(t=t.English)),t}},Object(v.c)(["addOderData","updateStatus","language","cityListData"])),mounted:function(){var t={company:this.user.companyName};console.log(this.user,"console.log(this.user)"),this.getCityList(t),this.options=this.citPotin(p.a)},components:{}},g={render:function(){var t=this,i=t.$createElement,e=t._self._c||i;return e("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"clearfix",attrs:{updateStatusData:t.updateStatusData,"element-loading-text":"请稍后...","element-loading-spinner":"el-icon-loading","element-loading-background":"#f7f7f7"}},[e("div",{staticClass:"fromBox left",staticStyle:{position:"relative"}},[e("el-form",{ref:"fromData",attrs:{"label-position":t.labelPosition,"label-width":"83px",model:t.fromData,rules:t.rules}},[e("el-form-item",{attrs:{label:"Chinese"==t.languageData.id?"国家":"country"}},[e("el-select",{attrs:{placeholder:"Chinese"==t.languageData.id?"请选择":"country"},on:{change:function(i){t.cityChange("country")}},model:{value:t.fromData.country,callback:function(i){t.$set(t.fromData,"country",i)},expression:"fromData.country"}},t._l(t.options,function(i){return e("el-option",{key:i.code,attrs:{label:"Chinese"==t.languageData.id?i.CN:i.EN,value:i.code}})}))],1),t._v(" "),e("el-form-item",{directives:[{name:"show",rawName:"v-show",value:t.provinceCheck,expression:"provinceCheck"}],attrs:{label:"Chinese"==t.languageData.id?"省份（州）":"province"}},[e("el-select",{attrs:{placeholder:"Chinese"==t.languageData.id?"请选择":"province"},on:{change:function(i){t.cityChange("province")}},model:{value:t.fromData.province,callback:function(i){t.$set(t.fromData,"province",i)},expression:"fromData.province"}},t._l(t.provincePtions,function(i){return e("el-option",{key:i.code,attrs:{label:"Chinese"==t.languageData.id?i.CN:i.EN,value:i.code}})}))],1),t._v(" "),e("el-form-item",{attrs:{label:"Chinese"==t.languageData.id?"城市":"city",prop:"city"}},[e("el-select",{attrs:{placeholder:"Chinese"==t.languageData.id?"请选择":"city"},on:{change:function(i){t.cityChange("city")}},model:{value:t.fromData.city,callback:function(i){t.$set(t.fromData,"city",i)},expression:"fromData.city"}},t._l(t.cityPtion,function(i){return e("el-option",{key:i.code,attrs:{label:"Chinese"==t.languageData.id?i.CN:i.EN,value:i.code}})}))],1),t._v(" "),e("div",{staticStyle:{"text-align":"center"}},[e("el-button",{staticClass:"submit",attrs:{type:"primary",round:""},on:{click:function(i){t.submitForm("fromData")}}},[t._v(" "+t._s("Chinese"==t.languageData.id?"选择":"Selected")+" ")])],1)],1),t._v(" "),e("div",{staticStyle:{position:"absolute",width:"100%",left:"0px",top:"-30px","font-weight":"bold"}},[e("p",{staticStyle:{"text-align":"center"}},[t._v(t._s(t.languageData.admin.checkCity))])])],1),t._v(" "),e("div",{staticClass:"cityBox left",staticStyle:{position:"relative"}},[e("div",{staticClass:"ctiye",attrs:{cityList:t.cityList}},t._l(t.countryData,function(i){return e("div",{staticStyle:{"text-align":"left"}},[e("div",{staticClass:"contyName"},[e("span",{staticClass:"Country"},[t._v("\n                        "+t._s("Chinese"==t.languageData.id?i.country.CN:i.country.EN))]),t._v(" "),e("div",{staticClass:"cleae"})]),t._v(" "),e("div",{staticClass:"cityList"},t._l(i.country.province,function(i){return e("div",{staticStyle:{"font-size":"12px","line-height":"30px"}},[e("span",{directives:[{name:"show",rawName:"v-show",value:"Chinese"==t.languageData.id,expression:"languageData.id=='Chinese'"}]},[t._v(t._s(i.CN?i.CN+"：":"")+" ")]),t._v(" "),e("span",{directives:[{name:"show",rawName:"v-show",value:"Chinese"!=t.languageData.id,expression:"languageData.id!='Chinese'"}]},[t._v(t._s(i.EN?i.EN+"：":"")+" ")]),t._v(" "),t._l(i.city,function(i){return e("span",{staticClass:"cityBoxText"},[t._v("\n              "+t._s("Chinese"==t.languageData.id?i.CN:i.EN)+"\n              "),e("i",{staticClass:"el-icon-circle-close",on:{click:function(e){e.stopPropagation(),t.delCityFn(i.id)}}})])})],2)}))])})),t._v(" "),e("el-button",{staticClass:"submit",staticStyle:{margin:"0px","margin-top":"10px"},attrs:{type:"primary",round:"",disabled:t.submintA},on:{click:function(i){t.updateCity()}}},[t._v(t._s("Chinese"==t.languageData.id?"提交":"Submit")+"  ")]),t._v(" "),e("div",{staticStyle:{position:"absolute",width:"100%",left:"0px",top:"-30px","font-weight":"bold"}},[e("p",[t._v(t._s(t.languageData.admin.CompanyCity))])])],1)])},staticRenderFns:[]},m=e("VU/8")(h,g,!1,function(t){e("CDzc")},"data-v-230b903c",null).exports,f={name:"my-city",data:function(){return{fromData:{userName:null,password:null,clientType:1},remember:!1,error:!1,defaultLayers:null,ui:null,list:[],bubble:null,routeInstructionsContainer:null,input:null}},methods:{login:function(){this.$router.push({name:"map"})}},computed:{},mounted:function(){this.$store.commit("updateNave",{logo:!1,login:!1,us:!0,language:!0,user:!1,nav:!1})},components:{HeaderPublc:n.a,cent:m}},C={render:function(){var t=this.$createElement,i=this._self._c||t;return i("div",{staticClass:"set"},[i("section",{attrs:{id:"header"}},[i("HeaderPublc")],1),this._v(" "),i("div",{attrs:{id:"cent"}},[i("cent")],1),this._v(" "),i("section",{attrs:{id:"footer"}})])},staticRenderFns:[]},N=e("VU/8")(f,C,!1,function(t){e("77zf")},"data-v-4fd3659f",null);i.default=N.exports}});
//# sourceMappingURL=7.0424fe2548a7924b881f.js.map