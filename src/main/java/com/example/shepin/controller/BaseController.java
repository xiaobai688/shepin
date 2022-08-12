package com.example.shepin.controller;

import com.example.shepin.client.OpenClient;

public class BaseController {

    String url = "http://release.sop-gateway.opechk.com";
    String appId = "20220721999640269739524096";
    /** 开发者私钥 */
    String privateKeyIsv = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMPuVFjxxEwU32wGAzUqvm0TCj7Z05ifQFzfEotO0vJFKj22VOHA0WAJmWW8CSBUzPZR5dKY2NTb2iSgDp+DM0PsnMwc5C3giPMsFzTIhGdwX7fqg7zkNNK9LOd6EkWP89ULllN+B17mV6UO8KHZEz0sxjWPdcSrK8hbuzsXMW8ZAgMBAAECgYBT4gNmmQtrbzxAKUrSRlyEojQuuF9o5jVODYsI7VROuOHezeMFS+HX9gQglCJsHyIpCuMUCLA8YWvBZm0cSbV2TVPPDY2+46IyoQReDksUagcuVrG3+0vrt7d6PyJkYBxHfFEn9rnIcfbfrgYzRJ9XbzBQarmhy9WDT1IZ40IiAQJBAOT+6Yu6u49c1gChBtUSnJ+83OlYxQfmi2BAZa4/uzxCkX1OK0/WYA+nxlVl0fNGlbaOyqlSfHMoW//Ji1ilh7kCQQDbCTvqJlJ2Pf6X6wwvsFJHA5pDGNeEGKOOBKGPan7Fev+PAYRHW4n2iStgRkeGArQ2VTOnLpv7SmWw6Wi/XhJhAkEAyB3cireM4SQIAFS4W8wAA7hQdbuVs+nNMzgTo8seHPMvoYmGYCa/m+7KbCam6ypr+zWd9YqYJyrF+ypuE/HUQQJBAMsKWUbpdUF0oF+VHDvMt8hqd4JBMe6apGpOsrDuKEvJg8K9xjAbk908NEBPhZedRA6llncgRql/wppnE5lpi6ECQH5vPciuT7ZjyMnwMZuSjNbHc55QK1WLKrdK/h2POTeBkpsWKs5QJiNGqZyQmgdiDwmJ4MlH6kp6E0WSqEOZW5w=";
    /** 开放平台提供的公钥
     * 前往SOP-ADMIN，ISV管理--秘钥管理，生成平台提供的公私钥，然后把【平台公钥】放到这里 */
    String publicKeyPlatform = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApc329v729BsDmXc+chEBTLzKhnbHrWP47S5ptrIo59zIxreaHGSlsKUSAhpBM2SvuFzmdrtII33Uh3RfCTXocq+weCKuxPl4F2KR/XzyNrfQCfS8zxp7vpczzu7WiqPCseqoqkLb76QbAeGT0IO8bq8VDdNLxQ2/N5GOptzIU+MI6Ti9EXrr+GNVfnasLLESDThTzUbiWXc4Pz26qxKiAJL4CQZpBTWsgBhPU2sX9T5+i9dzxu6W0ZaIrwjHDDAVqk00E62fBtE4JYyxUUHY8xN2BGx5fTs9eO1xfYnYh2ZiR5HZl3duVqfwlD5QIUq+Yj5OaWvKJFB+VpNJQqX5GwIDAQAB";

    // 声明一个就行
    OpenClient client = new OpenClient(url, appId, privateKeyIsv, publicKeyPlatform);
}
