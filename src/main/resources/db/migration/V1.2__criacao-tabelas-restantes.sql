create table tb_movimento (
                              data_cadastro date not null,
                              data_pagamento date,
                              data_vencimento date not null,
                              recorrente bit not null,
                              status bit not null,
                              valor decimal(38,2) not null,
                              id bigint not null auto_increment,
                              usuario_id bigint not null,
                              observacoes varchar(255),
                              tipo_despesa enum ('AGUA','ALIMENTACAO','EDUCACAO','INTERNET','INVESTIMENTO','LAZER','LUZ','RESERVA_EMERGENCIA') not null,
                              titulo varchar(100) not null, primary key (id)
) engine=InnoDB default charset=utf8mb4;

create table tb_usuario_perfil (
                                   perfil_id bigint not null,
                                   usuario_id bigint not null,
                                   primary key (perfil_id, usuario_id)
) engine=InnoDB default charset=utf8mb4;

alter table tb_usuario add constraint UK_UNIQUE_EMAIL unique (email);
alter table tb_movimento add constraint FK_MOVIMENTO_USUARIO_ID foreign key (usuario_id) references tb_usuario (id);
alter table tb_usuario_perfil add constraint FK_PERFIL_ID foreign key (perfil_id) references tb_perfil (id);
alter table tb_usuario_perfil add constraint FK_USUARIO_ID foreign key (usuario_id) references tb_usuario (id);
