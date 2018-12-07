# OMDBApp

O OMDbApp é um app criado para o desafio do processo seletivo da Zup!

O app tem como principal objetivo buscar filmes da API do OMDb e salvar no dispositivo do usuário para possibilitar o uso offline!

* Arquitetura

A arquitetura MVVM tem como principal objetivo separar as responsabilidades das 
camadas de interação com o usuário das camadas que cuidam das regras de negócio.

Portanto o aplicativo trabalha com três fundamentos em cima deste conceito de arquitetura:

-- As activities são repsonsáveis pela informação que é apresentada ao usuário

-- Os controllers são responsáveis pela execução da regra de negócio e requisições http

-- Os event listeners, e em alguns casos os controllers, são responsáveis pela aquisição 
e retorno de informações para serem exibidos pelas activities

* BIBLIOTECAS DE TERCEIROS:

Além das bibliotecas nativas de Android como o SQLite para gerenciamento do banco de dados, duas bibliotecas principais devem ser destacadas:

-- Google Volley: responsável por executar as requisições http e adquirir as informações da API por JSON

-- Google GSON: responsável por serializar os objetos diretamente do JSON

* PONTOS DE MELHORIA:

Alguns elementos de interface podem ser melhorados em termos de dinamicidade do layout (alguns campos possuem tamanho fixo) e organização (o uso de RelativeLayout pode ser interessante na tela de Detalhes por exemplo). Layout esta bem básico. 

* PREVIEW:


