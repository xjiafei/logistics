webpackJsonp([3],{"6rku":function(a,e,t){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=t("mvHQ"),r=t.n(s),i=t("Dd8w"),o=t.n(i),n=t("orAT"),l=t("AMmF"),c=t("yKu/"),u=t.n(c),g=t("NYxO"),m={name:"my-login",data:function(){return{fromData:{userName:null,password:null,clientType:1},remember:!1,error:!1,defaultLayers:null,ui:null,list:[],bubble:null,routeInstructionsContainer:null,input:null,loading:!1,loginImg:u.a}},methods:o()({},Object(g.b)({lognPost:"loginFn"}),{login:function(){this.fromData.userName&&this.fromData.password&&(this.loading=!0,this.lognPost(this.fromData))},userFn:function(a){if(this.loading=!1,0!=a.status){this.error=!0;var e=this;return setTimeout(function(){e.error=!1},5e3),!1}var t=r()(a.data),s=a.data.token;if(l.a.publicLo.sessionStorageSav("token",s),l.a.publicLo.sessionStorageSav("user",t),this.remember){var i=this.fromData;i=r()(i),localStorage.setItem("userData",i)}else localStorage.setItem("userData","");1==a.data.type&&this.$router.push({name:"map"}),0==a.data.type&&this.$router.push({name:"AdminConfig"})}}),computed:o()({languageData:function(){var a=this.language;return a&&("Chinese"==a.check&&(a=a.Chinese),"English"==a.check&&(a=a.English)),a},user:function(){var a=this.userData,e=!1;return a&&(this.userFn(a),e=!0),e}},Object(g.c)(["language","userData"])),mounted:function(){this.$store.commit("updateNave",{logo:!1,login:!1,us:!0,language:!0,user:!1,nav:!1});var a=localStorage.getItem("userData");if(a){a=JSON.parse(a),this.fromData={userName:a.userName,password:a.password},this.remember=!0;var e=this;setTimeout(function(){e.remember&&e.login()},5e3)}},components:{HeaderPublc:n.a}},d={render:function(){var a=this,e=a.$createElement,t=a._self._c||e;return t("div",{class:{English:"English"==a.languageData.id}},[t("section",{attrs:{id:"header"}},[t("HeaderPublc")],1),a._v(" "),t("section",{attrs:{id:"cent"}},[t("div",{staticClass:"box"},[t("div",{directives:[{name:"loading",rawName:"v-loading",value:a.loading,expression:"loading"}],staticClass:"center",attrs:{"element-loading-text":"正在登录请稍后....","element-loading-spinner":"el-icon-loading","element-loading-background":"rgba(255,255,255,0.7)"}},[t("div",{staticClass:"clearfix"},[t("div",{staticClass:"logoText logo",staticStyle:{float:"left"}},[t("img",{staticStyle:{width:"136px",height:"136px"},attrs:{src:a.loginImg}})]),a._v(" "),t("div",{staticClass:"logoText",staticStyle:{float:"left","margin-top":"84px"}},[a._v(a._s(a.languageData.header.logoText))]),a._v(" "),t("div",{staticClass:"cleae"})]),a._v(" "),t("div",{staticClass:"saech"},[t("div",{staticClass:"clearfix inputBox"},[t("el-input",{staticClass:"saechIput name",attrs:{"suffix-icon":"el-icon-circle-close-outline",placeholder:a.languageData.login.user},model:{value:a.fromData.userName,callback:function(e){a.$set(a.fromData,"userName",e)},expression:"fromData.userName"}}),a._v(" "),t("span",{staticClass:"clearName",on:{click:function(e){a.fromData.userName="",a.fromData.password=""}}}),a._v(" "),t("p",{class:{error:a.error,noterror:!a.error}},[t("span",{staticClass:"el-icon-warning",staticStyle:{"font-size":"14px"}}),a._v(" 您输入的账户或密码不正确，请重新输入。")])],1),a._v(" "),t("div",{staticClass:"clearfix"},[t("el-input",{staticClass:"saechIput password",attrs:{type:"password",placeholder:a.languageData.login.password},model:{value:a.fromData.password,callback:function(e){a.$set(a.fromData,"password",e)},expression:"fromData.password"}})],1),a._v(" "),t("div",{staticClass:"check"},[t("el-checkbox",{staticClass:"saechIput check",model:{value:a.remember,callback:function(e){a.remember=e},expression:"remember"}},[a._v(a._s(a.languageData.login.Automatic))])],1),a._v(" "),t("el-button",{staticClass:"saechBout",attrs:{type:"primary"},on:{click:function(e){a.login()}}},[a._v(" "+a._s(a.languageData.login.login)+" ")])],1)])])]),a._v(" "),t("section",{attrs:{id:"footer",user:a.user}})])},staticRenderFns:[]},p=t("VU/8")(m,d,!1,function(a){t("OL5O")},"data-v-4b376b20",null);e.default=p.exports},OL5O:function(a,e){},"yKu/":function(a,e,t){a.exports=t.p+"static/img/logo180.2433c53.png"}});
//# sourceMappingURL=3.7e03db621f1a23ac9e69.js.map