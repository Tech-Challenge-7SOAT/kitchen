Feature:
    Scenario: Buscar preparo por ID de
        Given que o usuário tenha um preparo registrado
        When o usuário busca o preparo por ID de pedido
        Then o usuário deve visualizar o preparo

    Scenario: Buscar preparo por ID de pedido inexistente
        When o usuário busca o preparo por ID de pedido inexistente
        Then o usuário deve visualizar a mensagem de erro

    Scenario: Enviar um novo pedido para preparo
        When o usuário envia um novo pedido para preparo
        Then o usuário deve visualizar o preparo do pedido

    Scenario: Atualiza o status do preparo
        Given que o usuário tenha um preparo registrado
        When o usuário atualiza o status do preparo
        Then o usuário deve visualizar o status do preparo atualizado
