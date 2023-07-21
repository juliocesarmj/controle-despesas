create table tb_usuario (
                            id bigint not null auto_increment,
                            nome varchar(255) not null,
                            email varchar(130) not null unique,
                            senha varchar(255) not null,
                            data_cadastro datetime not null,
                            data_atualizacao datetime,
                            ativo boolean default true,
                            primary key(id)

)engine=InnoDB default charset=utf8mb4;