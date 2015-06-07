//$(document).ready(function () {
//  $("#login").click(function () {
//    //window.location = ;
//    $.oauth2({
//      auth_url: 'https://secure.echosign.com/public/oauth',
//      response_type: 'code',
//      token_url: 'https://secure.echosign.com/public/oauth/token',
//      client_id: 'C7YL2R4Y3B3KXG',
//      client_secret: '_FyDcV9W9Iq7p81sDuzQuFCpDItjvMZ4',
//      redirect_uri: 'https://localhost:8080/authentication',
//      other_params: {scope: 'widget_read:self', display: 'popup'}
//    }, function (token, response) {
//      console.log(token);
//    }, function (error, response) {
//      console.log(error);
//    });
//  });
//});

var app = angular.module("APP", [
  "ngRoute", "satellizer"
]);

app.config(["$routeProvider", "$authProvider", "$httpProvider",
  function ($routeProvider, $authProvider, $httpProvider) {

    $authProvider.oauth2({
      name: 'esign',
      url: '/auth/esign',
      redirectUri: "https://localhost:8080/authentication",
      scope: ['widget_read:self', 'widget_write:self'],
      requiredUrlParams: ['scope'],
      scopeDelimiter: "+",
      clientId: 'C7YL2R4Y3B3KXG',
      authorizationEndpoint: 'https://secure.echosign.com/public/oauth'
    });


    $routeProvider
      .otherwise({
        redirectTo: "/"
      });
  }]);

app.factory("appService", function ($window, $http) {
  var instance = {
    getWidgetUrl: function (token, $scope) {
      $http.post("url", {token: token}).success(function (data) {
        console.log(data);
        $scope.widgetUrl = data.widgetUrl;
      });
    },

    createDocusignEnvelop: function ($scope) {
      $http.get("docusign/createEnvelope").success(function (data) {
        $("#docusign").attr("src", data.widgetUrl);
        //$scope.widgetUrl = $sce.getTrustedResourceUrl( data.widgetUrl );
        //console.log($scope.widgetUrl);
      });
    }
  };

  return instance;
});

app.controller("AppController", function ($scope, $auth, $window, appService, $sce) {
  appService.createDocusignEnvelop($scope);

  //$scope.videos = $sce.getTrustedResourceUrl('https://demo.docusign.net/Member/');

  $scope.createWidget = function () {
    $auth.authenticate("esign")
      .then(function (resp) {
        var token = $window.localStorage["satellizer_token"];
        appService.getWidgetUrl(token, $scope);
      });
  }
});
