CREATE DATABASE DBI_ASSIGNMENT_PRJ25
--SE161501- Le Trung Son, SE161942- Nguyen Giang Nam
USE DBI_ASSIGNMENT_PRJ25



CREATE TABLE Tenant (
	TenantID nvarchar(6) CONSTRAINT check_tenantid CHECK(TenantID LIKE 'TE[0-9][0-9][0-9][0-9]' ) not null, 
	TenantName nvarchar(50) not null, 
	IdentifyNumber nvarchar(50) unique not null, 
	Sex char(6) CONSTRAINT check_sex CHECK(Sex IN('Female','Male')) not null,
	Job nvarchar(50), 
	[Address] nvarchar(100), 
	PhoneNumber CHAR(10) unique CONSTRAINT check_phone CHECK(PhoneNumber LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]' ) not null, 
	TenantStatus nvarchar(15) DEFAULT 'Valid' CONSTRAINT check_tenantstatus CHECK(TenantStatus IN('Valid','Expired')),
	CONSTRAINT pk_tenant PRIMARY KEY (TenantID),
);



CREATE TABLE Room (
	RoomID nvarchar(6) CONSTRAINT check_roomid CHECK(RoomID LIKE 'RM[0-9][0-9][0-9][0-9]' ) not null,  
	[Floor] int CONSTRAINT check_floor CHECK([Floor] BETWEEN 1 AND 6) not null, 
	MaxPeople int DEFAULT 1 CHECK(MaxPeople BETWEEN 1 AND 4) not null,
	Price money not null,
	RoomStatus nvarchar(15) DEFAULT 'Empty' CONSTRAINT check_roomstatus CHECK(RoomStatus IN('Empty','Rented')) not null,
	CONSTRAINT pk_room PRIMARY KEY (RoomID)
);




CREATE TABLE [Service] (
	ServiceID nvarchar(6) CONSTRAINT check_serviceid  CHECK(ServiceID LIKE 'SE[0-9][0-9][0-9][0-9]' ) not null,
	ServiceName nvarchar(50) unique not null,
	ServicePrice money not null,
	CONSTRAINT pk_service PRIMARY KEY (ServiceID)
);



CREATE TABLE RentingManagement (
	RentingID nvarchar(6) CONSTRAINT check_rentingid CHECK(RentingID LIKE 'RT[0-9][0-9][0-9][0-9]' ) not null, 
	TenantID nvarchar(6) not null, 
	RoomID nvarchar(6) not null,
	RentingDate datetime CONSTRAINT check_rentingdate CHECK(RentingDate <GETDATE()) not null, 
	RentingStatus nvarchar(15) DEFAULT 'Valid' CONSTRAINT check_rentingstatus CHECK(RentingStatus IN('Valid','Expired')) not null,
	Deposit money not null,
	CONSTRAINT pk_rentingmanagement PRIMARY KEY (RentingID),
);
ALTER TABLE RentingManagement ADD CONSTRAINT fk_rentToTenant FOREIGN KEY (TenantID) REFERENCES Tenant (TenantID)
ALTER TABLE RentingManagement ADD CONSTRAINT fk_rentToHome FOREIGN KEY (RoomID) REFERENCES Room (RoomID)



CREATE TABLE Payment (
	PaymentID nvarchar(6) CONSTRAINT check_paymentid CHECK(PaymentID LIKE 'PY[0-9][0-9][0-9][0-9]' ) not null, 
	RoomID nvarchar(6) not null, 
	PaymentDate datetime not null,
	PaymentPrice money not null, 
	Reason nvarchar(50) not null,
	CONSTRAINT pk_payment PRIMARY KEY (PaymentID)
);
ALTER TABLE Payment ADD CONSTRAINT fk_paymentToRoom FOREIGN KEY (RoomID) REFERENCES Room (RoomID)




CREATE TABLE MoneyManagement (
	CollectionID nvarchar(6) CONSTRAINT check_collectionid CHECK(CollectionID LIKE 'MM[0-9][0-9][0-9][0-9]' ) not null, 
	ServiceID nvarchar(6) not null, 
	TenantID nvarchar(6) not null, 
	CollectionDate date not null, 
	RoomID nvarchar(6) not null,
	CONSTRAINT pk_moneymanagement PRIMARY KEY (CollectionID),
);
ALTER TABLE MoneyManagement ADD CONSTRAINT fk_moneyToService FOREIGN KEY (ServiceID) REFERENCES [Service] (ServiceID)
ALTER TABLE MoneyManagement ADD CONSTRAINT fk_moneyToTenant FOREIGN KEY (TenantID) REFERENCES Tenant (TenantID)
ALTER TABLE MoneyManagement ADD CONSTRAINT fk_moneyToRoom FOREIGN KEY (RoomID) REFERENCES Room (RoomID)



CREATE TABLE Equipment (
	EquipmentID nvarchar(6) CONSTRAINT check_equipmentid CHECK(EquipmentID LIKE 'EQ[0-9][0-9][0-9][0-9]' ) not null, 
	EquipmentName nvarchar(50) not null,
	Brand nvarchar(50), 
	Quantity int not null, 
	EquipmentStatus nvarchar(15) DEFAULT '2ndhand' CONSTRAINT check_equipmentstatus CHECK(EquipmentStatus IN('New','2ndhand'))not null,
	RoomID nvarchar(6) not null,
	EquipmentPrice money not null,
	CONSTRAINT pk_equipment PRIMARY KEY (EquipmentID),
);
ALTER TABLE Equipment ADD CONSTRAINT fk_equipmentToRoom FOREIGN KEY (RoomID) REFERENCES Room (RoomID)



CREATE TABLE CheckOut (
	CheckOutID nvarchar(6) CONSTRAINT check_checkoutid CHECK(CheckOutID LIKE 'CO[0-9][0-9][0-9][0-9]' ) not null, 
	TenantID nvarchar(6) not null, 
	CheckOutDate date not null,
	Note nvarchar(50),
	CONSTRAINT pk_checkout PRIMARY KEY (CheckOutID),
);
ALTER TABLE CheckOut ADD CONSTRAINT fk_checkOutToTenant FOREIGN KEY (TenantID) REFERENCES Tenant (TenantID)





















