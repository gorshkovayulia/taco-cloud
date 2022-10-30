create table taco_order (
  id bigint primary key,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table taco (
  id bigint primary key,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);

create table ingredient_ref (
  ingredient varchar(4) not null,
  taco bigint not null,
  taco_key bigint not null
);

create table ingredient (
  id varchar(4) primary key,
  name varchar(25) not null,
  type varchar(10) not null
);

alter table taco add constraint fk_taco_taco_orders foreign key (taco_order) references taco_order(id);
alter table ingredient_Ref add foreign key (ingredient) references ingredient(id);