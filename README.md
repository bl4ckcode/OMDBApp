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

![alt text](https://lh3.googleusercontent.com/sEW1Shkbd3SLCHdjgrr5su3g-oDj17goLjh-qltzi2PB0qTcZ2dj5ddMk2taQ7tQUv7CR1qgPmAnFM97b63SFg=w1600-h744-rw)

![alt text](https://lh3.googleusercontent.com/Hq6n0tI7c_txCfWuXflyerKwUqoBz_hzMfMhsTryfIrzCedruU4IeBSKucnHrJFSGv8zPA5Ta_ybwaKPL3UaMA=w1600-h744-rw)

![alt text](https://lh3.googleusercontent.com/P4TuC-C1lapbpPyYW8qWFkYbXLzY5vebI-PLAage-geBvubG8OPh_0iaLIz9uVZKlGV1QynIE7hrgulPqVuq8w=w1600-h744-rw)

![alt text](https://lh5.googleusercontent.com/_MSQb6Is7Jn_49jzpvdU6I0w1guT57oFIlbrZLZeiDPzk-Vf9_AU5yIphv93ATAfehZItHWOh6ROokgGdzWNig=w1600-h744-rw)

![alt text](https://lh4.googleusercontent.com/PKfOg6IkbJ9rRtcBXrJnhYEKRKmlBwGscAbWaLXT6wgqvi68UpTZcFps1RuX_8Qe1rFOaiUMuDyk9k86C62JYg=w1600-h744-rw)

![alt text](https://lh5.googleusercontent.com/6GknIgtWY9-5uKA0jmKu0m3zfU1koF0y9j0cPRTvL2hd831sN_CX3CA-bHix2sue2H2dWN7EO1FR3o5vzfrNGg=w1600-h744-rw)
