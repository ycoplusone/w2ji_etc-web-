package book_store;

//book_store 도서 대여 시스템
public class BookStore {

 // id 기본키
 private Integer id;

 // nm 이름
 private String nm;

 // writer 저자
 private String writer;

 // price 가격
 private Integer price;

 // rental_yn 대여여부
 private String rentalYn;

 // count 대여횟수
 private Integer count;

 public Integer getId() {
     return id;
 }

 public void setId(Integer id) {
     this.id = id;
 }

 public String getNm() {
     return nm;
 }

 public void setNm(String nm) {
     this.nm = nm;
 }

 public String getWriter() {
     return writer;
 }

 public void setWriter(String writer) {
     this.writer = writer;
 }

 public Integer getPrice() {
     return price;
 }

 public void setPrice(Integer price) {
     this.price = price;
 }

 public String getRentalYn() {
     return rentalYn;
 }

 public void setRentalYn(String rentalYn) {
     this.rentalYn = rentalYn;
 }

 public Integer getCount() {
     return count;
 }

 public void setCount(Integer count) {
     this.count = count;
 }

 // BookStore 모델 복사
 public void CopyData(BookStore param)
 {
     this.id = param.getId();
     this.nm = param.getNm();
     this.writer = param.getWriter();
     this.price = param.getPrice();
     this.rentalYn = param.getRentalYn();
     this.count = param.getCount();
 }
}