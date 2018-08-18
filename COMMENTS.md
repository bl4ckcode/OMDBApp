# WeatherApp

* ARQUITETURA MVVM:

A arquitetura MVVM tem como principal objetivo separar as responsabilidades das 
camadas de interação com o usuário das camadas que cuidam das regras de negócio.

Portanto o aplicativo trabalha com três fundamentos em cima deste conceito de arquitetura:
- As activities são repsonsáveis pela informação que é apresentada ao usuário
- Os controllers são responsáveis pela execução da regra de negócio e requisições http
- Os event listeners, e em alguns casos os controllers, são responsáveis pela aquisição 
e retorno de informações para serem exibidos pelas activities

* BIBLIOTECAS DE TERCEIROS:

Além das bibliotecas nativas de Android, duas bibliotecas principais devem ser destacadas:

- Google Volley: responsável por executar as requisições http e adquirir as informações da API por JSON
- Google GSON: responsável por realizar o parsing do JSON do arquivo citiy.list.json e da API