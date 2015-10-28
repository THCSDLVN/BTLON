public ResultSet dataQuery(String tableName, String listColumn,String on, String condition, String groupBy, String having, String orderBy, String arrangement)

/*
hàm truy xuất dữ liệu - sử dụng câu lệnh SELECT FROM
Trả về ResultSet
tableName = Bảng1,Bảng2,.....
listColumn = cột1,cột2,....(*)
on = key trong inner join(chỉ được 1 key tại 1 thoi diem truy van)
condition = giống như khi viết câu lệnh SQL (VD:AID = 'ACC0001' and RID = 'RES0001')
group by = giống như khi viết câu lệnh SQL
having = tương tự trên
OrderBy = tương tự trên
arrangement = ASC || DESC
tableName và listColumn bắt buộc khác rỗng
các thành phần còn lại có thể bằng rỗng("","","") - nhưng buộc phải có trong câu lệnh
*/
public int deleteDataQuery(String tableName, String condition)

/*
hàm sử dụng để xóa dữ liệu
tableName = Bảng1 (Chỉ xóa được dữ liệu của 1 bảng duy nhất)
condition = tương tự như khi viết cấu lệnh SQL (VD: AID = 'ACC0001' and RID = 'RES0001')
tableName, condition bắt buộc phải khác rỗng
*/

public int updateDataQuery(String tableName, String set, String value, String condition)
/*
hàm sử dụng để update dữ liệu - Sử dụng câu lệnh UPDATE 
tableName = Bảng1 (Chỉ update dữ liệu của 1 bảng duy nhất)
set = tên của cột muốn update dữ liệu(chi update dc 1 cột duy nhất)
value = giá trị mới của cột
condition = tương tự như câu lệnh SQl (AID = 'ACC0001')

tableName và set phải khác rỗng
condition có thể bằng rỗng("") - nhưng buộc phải có trong câu lệnh
*/

public int insertDataQuery(String tableName, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8)
/*
hàm sử dụng để nhập dữ liệu vào bảng - Sử dụng câu lệnh INSERT 
tableName = Bảng1 (Chỉ insert được dữ liệu của 1 bảng duy nhất)
Các thành phần khóa chính ở các bảng phải khác rỗng
value1 ---> value8
Restaurant  -- ("Restaurant",rid,restaurantName,address,phone,face,rate,resName,resPass)
Account     -- ("Account",aid,fullName,userName,password,phone,face,"","")
FoodShip    -- ("FoodShip",rid,aid,foodlist,address,time,"","","")
Menu        -- ("Menu",fid,foodName,"","","","","","")
Provide     -- ("Provide",rid,fid,cost,"","","","","")
Reservation -- ("Reservations",rid,aid,foodlist,time,"","","","")
tên các bảng, cột chỉ chứa : Chữ cái, chữ số, _
*/
