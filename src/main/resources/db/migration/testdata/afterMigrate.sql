set foreign_key_checks = 0;

delete from tb_perfil;
delete from tb_usuario;
delete from tb_usuario_perfil;
delete from tb_movimento;

set foreign_key_checks = 1;

alter table tb_perfil auto_increment = 1;
alter table tb_usuario auto_increment = 1;
alter table tb_movimento auto_increment = 1;

insert into tb_perfil(tipo) values ('ROLE_FREE'), ('ROLE_PREMIUM'), ('ROLE_ADMIN'), ('ROLE_APLICACAO');

insert into tb_usuario(nome, email, senha, data_cadastro, ativo) values ('teste user free', 'teste-free@teste.com', '$2a$04$hr6vIyarkO/lTmLSDzFQjOHiwLTmR55NxVxa6fGDWFMT6rnBYsUKK', '2023-07-29 09:11:16', true);
insert into tb_usuario(nome, email, senha, data_cadastro, ativo) values ('teste user premium', 'teste-premium@teste.com', '$2a$04$hr6vIyarkO/lTmLSDzFQjOHiwLTmR55NxVxa6fGDWFMT6rnBYsUKK', '2023-07-29 09:11:16', true);
insert into tb_usuario(nome, email, senha, data_cadastro, ativo) values ('teste user admin', 'teste-admin@teste.com', '$2a$04$hr6vIyarkO/lTmLSDzFQjOHiwLTmR55NxVxa6fGDWFMT6rnBYsUKK', '2023-07-29 09:11:16', true);
insert into tb_movimento(titulo, tipo_despesa, valor, data_cadastro, data_vencimento, pago, recorrente, usuario_id)
values ('Conta de internet', 'INTERNET', 99.90, '2023-07-15', '2023-08-16', false, true, 3), ('Conta de luz', 'LUZ', 20.85, '2023-07-15', '2023-08-06', false, true, 3),
       ('Boleto DAS MEI', 'OUTROS', 70.85, '2023-07-15', '2023-08-06', false, true, 1);
insert into tb_usuario_perfil(usuario_id, perfil_id) values (1, 1), (2, 2), (3, 2), (3, 3);