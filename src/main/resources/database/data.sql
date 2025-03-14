-- Inserir 10 usuários com role USER
INSERT INTO usuarios (nome_usuario, email_usuario, senha_usuario, data_criacao, ultimo_login, role, recovery_token, token_expiration)
VALUES
('johndoe', 'johndoe@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('janedoe', 'janedoe@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('mariasilva', 'mariasilva@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('pedroalves', 'pedroalves@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('luanabrito', 'luanabrito@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('rodrigoborges', 'rodrigoborges@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('camilarocha', 'camilarocha@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('viniciusgomes', 'viniciusgomes@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('alinedias', 'alinedias@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL),
('felipepereira', 'felipepereira@example.com', 'password123', NOW(), NOW(), 'USER', NULL, NULL);

-- Inserir 10 projetos
INSERT INTO projetos (nome_projeto, descricao_projeto, data_inicio, data_fim, status_projeto, id_usuario_responsavel, data_criacao, prioridade_projeto)
VALUES
('Projeto Alpha', 'Projeto para desenvolver um sistema de gerenciamento de tarefas', '2025-03-01', '2025-12-31', 0, 1, NOW(), 'ALTA'),
('Projeto Beta', 'Desenvolvimento de um sistema de gestão de equipes', '2025-02-15', '2025-11-30', 1, 2, NOW(), 'MÉDIA'),
('Projeto Gamma', 'Sistema de automação de vendas', '2025-04-01', '2025-10-01', 2, 3, NOW(), 'BAIXA'),
('Projeto Delta', 'Aplicativo de rastreamento de saúde', '2025-03-15', '2025-08-15', 1, 4, NOW(), 'ALTA'),
('Projeto Epsilon', 'Plataforma de aprendizado online', '2025-05-01', '2025-12-15', 0, 5, NOW(), 'MÉDIA'),
('Projeto Zeta', 'Solução de e-commerce para pequenas empresas', '2025-06-01', '2025-11-30', 2, 6, NOW(), 'ALTA'),
('Projeto Eta', 'Sistema de agendamento de consultas médicas', '2025-07-01', '2025-10-01', 0, 7, NOW(), 'MÉDIA'),
('Projeto Theta', 'Plataforma de delivery de comida', '2025-08-01', '2025-12-31', 1, 8, NOW(), 'ALTA'),
('Projeto Iota', 'Sistema de automação de processos administrativos', '2025-05-15', '2025-11-01', 2, 9, NOW(), 'MÉDIA'),
('Projeto Kappa', 'Plataforma de streaming de vídeos educacionais', '2025-09-01', '2025-12-01', 0, 10, NOW(), 'ALTA');

-- Inserir 10 atividades com dados de DTO ajustados
INSERT INTO atividades (nome_atividade, descricao_atividade, data_inicio, data_fim, status_atividade, id_projeto, id_usuario_responsavel, data_criacao, ativo)
VALUES
('Planejamento Inicial', 'Planejamento das primeiras atividades do projeto Alpha', '2025-03-01', '2025-03-10', 'EM_ANDAMENTO', 1, 1, NOW(), 1),
('Desenvolvimento Backend', 'Desenvolvimento da API do projeto Beta', '2025-02-15', '2025-03-30', 'PENDENTE', 2, 2, NOW(), 1),
('Análise de Requisitos', 'Reuniões para levantamento de requisitos do Projeto Gamma', '2025-04-01', '2025-04-15', 'CONCLUIDO', 3, 3, NOW(), 1),
('Design de Interface', 'Criação do layout do Projeto Delta', '2025-03-15', '2025-03-30', 'EM_ANDAMENTO', 4, 4, NOW(), 1),
('Desenvolvimento Frontend', 'Desenvolvimento da interface do Projeto Epsilon', '2025-05-01', '2025-06-15', 'PENDENTE', 5, 5, NOW(), 1),
('Testes de Funcionalidade', 'Testes de todas as funcionalidades do Projeto Zeta', '2025-06-01', '2025-06-30', 'PENDENTE', 6, 6, NOW(), 1),
('Implementação de API', 'Desenvolvimento da API do Projeto Eta', '2025-07-01', '2025-07-15', 'EM_ANDAMENTO', 7, 7, NOW(), 1),
('Entrega Parcial', 'Entrega do protótipo do Projeto Theta', '2025-08-01', '2025-08-15', 'CONCLUIDO', 8, 8, NOW(), 1),
('Desenvolvimento de Módulo', 'Desenvolvimento do módulo de relatórios do Projeto Iota', '2025-05-15', '2025-06-30', 'PENDENTE', 9, 9, NOW(), 1),
('Integração de Sistemas', 'Integração com sistemas externos no Projeto Kappa', '2025-09-01', '2025-09-15', 'EM_ANDAMENTO', 10, 10, NOW(), 1);

-- Inserir 10 lançamentos de horas com DTO ajustado
INSERT INTO lancamento_horas (id_atividade, id_usuario, descricao, data_inicio, data_fim, data_registro, tempo_duracao)
VALUES
(1, 1, 'Planejamento das funcionalidades principais do projeto', '2025-03-01 08:00:00', '2025-03-01 10:00:00', NOW(), '02:00:00'),
(2, 2, 'Desenvolvimento das rotas para a API', '2025-02-15 09:00:00', '2025-02-15 17:00:00', NOW(), '08:00:00'),
(3, 3, 'Reuniões com o cliente para levantamento de requisitos', '2025-04-01 10:00:00', '2025-04-01 12:00:00', NOW(), '02:00:00'),
(4, 4, 'Criação do protótipo de design para a interface', '2025-03-15 14:00:00', '2025-03-15 17:00:00', NOW(), '03:00:00'),
(5, 5, 'Desenvolvimento do frontend para a tela de login', '2025-05-01 08:00:00', '2025-05-01 12:00:00', NOW(), '04:00:00'),
(6, 6, 'Realização de testes unitários para o sistema', '2025-06-01 10:00:00', '2025-06-01 18:00:00', NOW(), '08:00:00'),
(7, 7, 'Desenvolvimento da integração da API com o sistema', '2025-07-01 09:00:00', '2025-07-01 13:00:00', NOW(), '04:00:00'),
(8, 8, 'Entrega do protótipo para a revisão do cliente', '2025-08-01 08:00:00', '2025-08-01 12:00:00', NOW(), '04:00:00'),
(9, 9, 'Desenvolvimento do módulo de geração de relatórios', '2025-05-15 14:00:00', '2025-05-15 18:00:00', NOW(), '04:00:00'),
(10, 10, 'Integração de sistemas de pagamento externos', '2025-09-01 08:00:00', '2025-09-01 12:00:00', NOW(), '04:00:00');
