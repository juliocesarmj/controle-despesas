create table tb_perfil (
                           id bigint not null auto_increment,
                           tipo enum('ROLE_FREE', 'ROLE_PREMIUM','ROLE_ADMIN', 'ROLE_APLICACAO'),
                           primary key (id)
)engine=InnoDB default charset=utf8mb4;