package com.firstweb.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PageNavigator { //새글 이나 전체 페이지수 출력확인 Dto의 성격은 갖고있지만 다르다.
   private String root;
   private boolean nowFirst; // 현재가 첫번째범위에속해있느냐 현재가 첫번째 1~10에 속해있으면 이전버튼이 안눌러지게한다.
   private boolean nowEnd; // 현재가 마지막범위에 속해있느냐 현재가 마지막 페이지 ㅁ~ㅁ 에 속해있으면 다음버튼이 안눌러지게한다.
   private int totalArticleCount; // 전체글 갯수
   private int newArticleCount; // 새글 갯수
   private int totalPageCount; // 전체 페이지
   private int pageNo; // 현재페이지
   private String navigator;
   private int pageSize;
   private int viewListSize;
   Map<String, String> pageMap;

   //현재 페이지 번호, 총 개시물 수, 루트경로, 페이지네이션에 사이즈, 리스트 사이즈 (화면에 뿌릴)
   public PageNavigator(int pageNo, int totalArticleCount, String root, int pageSize, int viewListSize){
      pageMap = new HashMap<String,String>();
      this.totalArticleCount = totalArticleCount;
      this.pageNo = pageNo;
      this.root = root;
      this.pageSize = pageSize;
      this.viewListSize = viewListSize;
      setPaganationMap(pageNo);
      this.setNavigator();
   }
   public PageNavigator(String pageNo, int totalArticleCount, String root, int pageSize, int viewListSize){
      pageMap = new HashMap<String,String>();
      this.totalArticleCount = totalArticleCount;
      int temp_pageno =0;
      if(pageNo == null || "".equals(pageNo)) {
         temp_pageno = 1;
      }else {
         temp_pageno = Integer.parseInt(pageNo);
      }
      this.pageNo = temp_pageno;
      this.root = root;
      this.pageSize = pageSize;
      this.viewListSize = viewListSize;
      setPaganationMap(temp_pageno);
      this.setNavigator();
   }
   public void setPaganationMap(int pageNo){

      int start = ( (pageNo-1) * viewListSize );
      int end = viewListSize;
      pageMap.put("start_index", start+"");
      pageMap.put("end_index", end+"");
   }
   public Map<String, String> getPageMap( Map<String, String> paramterMap){
      for(Entry<String,String> entry :paramterMap.entrySet()){
         pageMap.put(entry.getKey(), entry.getValue());
      }
      return this.pageMap;
   }
   public Map<String, String> getPageMap(){
      return this.pageMap;
   }
   public String getRoot() {
      return root;
   }
   public void setRoot(String root) {
      this.root = root;
   }
   public boolean isNowFirst() {
      return nowFirst;
   }
   public void setNowFirst(boolean nowFirst) {
      this.nowFirst = nowFirst;
   }
   public boolean isNowEnd() {
      return nowEnd;
   }
   public void setNowEnd(boolean nowEnd) {
      this.nowEnd = nowEnd;
   }
   public int getTotalArticleCount() {
      return totalArticleCount;
   }
   public void setTotalArticleCount(int totalArticleCount) {
      this.totalArticleCount = totalArticleCount;
   }
   public int getNewArticleCount() {
      return newArticleCount;
   }
   public void setNewArticleCount(int newArticleCount) {
      this.newArticleCount = newArticleCount;
   }
   public int getTotalPageCount() {
      return totalPageCount;
   }
   public void setTotalPageCount(int totalPageCount) {
      this.totalPageCount = totalPageCount;
   }
   public int getPageNo() {
      return pageNo;
   }
   public void setPageNo(int pageNo) {
      this.pageNo = pageNo;
   }
   public String getNavigator() {
      return navigator;
   }
   public void setNavigator() {
      //로직
      //jsp에서는 하나의 function만 정의하면 된다.
      //function이름을 listarticle()로 하겠다.
      //listarticle()에 매개변수값은 이동할 페이지 번호이다.
      //맨처음으로 갈때는 매개변수를 1를 넘겨주면 된다
      //특정페이지는 특정 페이지 값을 넘겨주면 된다.
      //다음 페이지를 눌렀을 경우에는  다음페이지를 연산 후 매개변수로 넘겨준다
      //마지막 페이지도 마지막 페이지번호를 매개변수로 넘겨준다
      //이와같은 방법으로 페이지네이션을 한다.
      //MVC 패턴에 안맞지만 그냥 쓰도록 why? 편리하니깐

      StringBuffer tmpNavigator = new StringBuffer();
      int totalPageCount;

      //jsp에서 정의해야되는 함수
      String javascriptFunctionName="javascript:listarticle";

      //css  <ul>클래스
      String defaultClassUl="pagenation-ul";

      //css <li>클래스 (비활성화 할 css)
      String disabledClassLi="nation_disabled";

      //css <li>클래스 (활성화 할 css)
      String activeClassLi="dn";

      // 기본 <a>클래스
      String defaultClassA="position50";

      //총 몇페이지 있는지 구한다. 아래는 반올림 하는 부분 (Math 클래스를 쓰면 형변환 하기 때문에 아래와 같은 방법으로 한다)
      if(totalArticleCount % viewListSize == 0){
         totalPageCount = totalArticleCount / viewListSize;
      }else{
         totalPageCount = totalArticleCount / viewListSize+1;
      }

      int prePage = (pageNo -1)/ pageSize * pageSize;
      //[이전]버튼 눌렀을 때 이동될 페이지 번호

      // [이전, 처음]을 활성화/비활성화 정함
      if( pageNo <= pageSize  ){
         disabledClassLi="dn";
      }else{
         disabledClassLi="";
      }

      int startPage = prePage + 1;
      int endPage = prePage + pageSize;
      if(endPage > totalPageCount){
         endPage = totalPageCount;
      }
      pageNo = pageNo -1;
      tmpNavigator.append(" <ul class='"+defaultClassUl+"'> \n");
      tmpNavigator.append("    <li class='"+disabledClassLi+"' onclick=\"location.href='"+javascriptFunctionName+"(1)'\"><a class='"+defaultClassA+"'><span> << </span></a></li> \n");
      tmpNavigator.append("    <li class='"+disabledClassLi+"' onclick=\"location.href='"+javascriptFunctionName+"("+pageNo+")'\"><a class='"+defaultClassA+"'><span> <  </span></a></li> \n");
      pageNo = pageNo +1;
      //누른 페이지 클래스 주기
      for (int i = startPage; i <= endPage; i++) {
         if (pageNo == i) {
            activeClassLi = "nation_active";
         } else {
            activeClassLi = " ";
         }
         tmpNavigator.append("    <li class='"+activeClassLi+"' onclick=\"location.href='"+javascriptFunctionName+"("+i+")'\"><a class='"+defaultClassA+"'><span>"+i+"</span></a></li> \n");
      }

      //다음 페이지 눌렀을 때 페이지 번호 값
      int nextPage =  ( ( ( ( pageNo -1 ) / pageSize ) + 1 ) * pageSize ) +1;
      // [다음, 맨 끝]을 활성화/비활성화 할지 정함
      if(( ( pageNo -1 ) / pageSize ) < (totalPageCount/pageSize)-1){
         disabledClassLi="";
      }else{
         disabledClassLi="dn";
      }

      pageNo = pageNo +1;
      tmpNavigator.append("    <li class='"+disabledClassLi+"' onclick=\"location.href='"+javascriptFunctionName+"("+pageNo+")'\"><a class='"+defaultClassA+"'><span> >  </span></a></li> \n");
      tmpNavigator.append("    <li class='"+disabledClassLi+"' onclick=\"location.href='"+javascriptFunctionName+"("+nextPage+")'\"><a class='"+defaultClassA+"'><span> >> </span></a></li> \n");
      tmpNavigator.append(" </ul> ");
      pageNo = pageNo -1;

      //스크립트 생성
      this.navigator = tmpNavigator.toString();

   }
}