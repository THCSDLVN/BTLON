INSERT INTO `Permissions` (PRMID,PermissionName,OBJID,OPID)
VALUES
('PERM0001','RUD-Account','OBJT0002','OPER0011'),/* Doc,Cap nhat,Xoa du lieu tren bang Account.*/
('PERM0002','RI-Restaurant','OBJT0004','OPER0012'),/*Doc,Them du lieu tren bang Restaurant.*/
('PERM0003','RIU-Restaurant','OBJT0004','OPER0014'),/* Doc,Them,Cap Nhat du lieu tren bang Restaurant.*/
('PERM0004','R-SequenceRestaurant','OBJT0005','OPER0008'),/* Doc du lieu tren bang SequenceRestaurant. */
('PERM0005','RIUD-SequenceRestaurant','OBJT0005','OPER0015'),/* Doc,Them,Cap Nhat,Xoa du lieu tren bang SequenceRestaurant.*/
('PERM0006','R-Provide','OBJT0006','OPER0008'),/* Doc du lieu tren bang Provide.*/
('PERM0007','RIUD-Provide','OBJT0006','OPER0015'),/* Doc,Them,Cap nhat,Xoa du lieu tren bang Provide.*/
('PERM0008','RIUD-Reservation','OBJT0001','OPER0015'),/* Doc,Them,Cap nhat,Xoa du lieu tren bang Reservation.*/
('PERM0009','RD-Reservation','OBJT0001','OPER0009'),/* Doc,Xoa du lieu tren bang Reservation.*/
('PERM0010','RU-SequenceRestaurant.NumberLike','OBJT0005','OPER0010'),/* Xem,Sua du lieu tren truong Like cua bang SequenceRestaurant.*/
('PERM0011','R-SequenceRestaurant.NumberLike','OBJT0005','OPER0008');/*Doc du lieu tren truong Like cua bang SequenceRestaurant.*/
