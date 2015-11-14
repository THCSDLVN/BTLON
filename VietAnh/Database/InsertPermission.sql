Order - 0001
Account - 0002
Manage - 0003
Restaurant - 0004
SequenceRestaurant - 0005
Provide - 0006
RIUD
INSERT INTO `BT_LON`.`Permissions` (PRMID,PermissionName,OBJID,OPID)
VALUES
('PERM0001','RUD-Account','OBJT0002','OPER0011'),-- Doc,Cap nhat,Xoa du lieu tren bang Account.
('PERM0002','RI-Restaurant','OBJT0004','OPER0012'),--Doc,Them du lieu tren bang Restaurant.
('PERM0003','RIU-Restaurant','OBJT0004','OPER0014'),-- Doc,Them,Cap Nhat du lieu tren bang Restaurant.
('PERM0004','R-SequenceRestaurant','OBJT0005','OPER0008'), --Doc du lieu tren bang SequenceRestaurant. 
('PERM0005','RIUD-SequenceRestaurant','OBJT0005','OPER0015'),-- Doc,Them,Cap Nhat,Xoa du lieu tren bang SequenceRestaurant.
('PERM0006','R-Provide','OBJT0006','OPER0008'),-- Doc du lieu tren bang Provide.
('PERM0007','RIUD-Provide','OBJT0006','OPER0015'),-- Doc,Them,Cap nhat,Xoa du lieu tren bang Provide.
('PERM0008','RIUD-Order','OBJT0001','OPER0015'),-- Doc,Them,Cap nhat,Xoa du lieu tren bang Order.
('PERM0009','RD-Order','OBJT0001','OPER0009'),-- Doc,Xoa du lieu tren bang Order.
('PERM0010','RU-SequenceRestaurant.Like','OBJT0007','OPER0010'), -- Xem,Sua du lieu tren truong Like cua bang SequenceRestaurant.
('PERM0011','R-SequenceRestaurant.Like','OBJT0007','OPER0008');-- Doc du lieu tren truong Like cua bang SequenceRestaurant.
