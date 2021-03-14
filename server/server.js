const http = require('http');
const { URL } = require('url');
const faker = require('faker');
const crypto= require('crypto');

faker.locale = "pt_BR";

const localhost = '127.0.0.1';
const port = 8000;

/* CÓDIGO VULNERÁVEL */

/*

function validateEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email)
}

const server = http.createServer((req, res) => {

    
    // Uma das primeiras possíveis vulnerabilidades encontradas aqui é a API utilizar o campo de email
    como query parameter. Expor dados possívelmente sensíveis do usuário no próprio path de chamada não é
    considerado uma boa prática. O ideal seria utilizar uma chave única de identificação (como o id do funcionário, por exemplo) //
    
    const requestUrl = new URL(`http://${localhost}:${port}` + req.url)
    const email = requestUrl.searchParams.get('email')
    
    // É importante que os status code de resposta HTTP do servidor sejam precisos 
    para evitar que o atacante possa identificar regras de negócio do sistema. 
    Mensagens de erro não devem conter informações que possam identificar fluxos da aplicação //

    const validEmail = validateEmail(email)
    if(!validEmail){
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/plain');
        // Neste caso, a mensagem diz que o e-mail informado é inválido ou não existe no sistema.
        Isto dá margem para o atacante conhecer fluxos de validação e até mesmo fluxo de dados da aplicação. //
        res.end('Error: email is not valid or does not exist in the system.')
        return
    }

    // Além disso, possíveis headers utilizados devem ser mascarados (com criptografias, hashes, ou algo assim)
    para evitar expor dados de identificação de autenticação do usuário. 
    Veja que aqui foi retornado um header Authorization na response informado a senha do usuário 
    (aqui foi gerada uma senha aleatória apenas para fins explicativos). //

    res.setHeader('Content-Type', 'application/json');
    res.setHeader('Authorization', `Basic ${faker.internet.password()}`);

    res.statusCode = 200;
    // Por fim, a aplicação que irá consumir esta API necessita apenas do nome e da região do Funcionário,
    porém, o servidor está retornando uma série de informações sensíveis sobre o funcionário, como identificador,
    email, telefone e até mesmo endereço, deixando a cargo do cliente que consome a API fazer a filtragem dos dados. //
    res.end(JSON.stringify({
        id: faker.random.number(),
        name: faker.name.findName(),
        email,
        phone: faker.phone.phoneNumber(),
        address: faker.address.state(),
        streetAddress: faker.address.streetAddress()
    }));
});

*/

function encrypt(text) {
    let cipher = crypto.createCipheriv('aes-256-cbc', Buffer.from(crypto.randomBytes(32)), crypto.randomBytes(16));
    let encrypted = cipher.update(text);
    encrypted = Buffer.concat([encrypted, cipher.final()]);
    return { iv: crypto.randomBytes(16).toString('hex'), encryptedData: encrypted.toString('hex') };
}

const server = http.createServer((req, res) => {

    /* Utilizando parâmetros que não expoem dados pessoais */
    const requestUrl = new URL(`http://${localhost}:${port}` + req.url)
    const userId = requestUrl.searchParams.get('userId')
    

    const validUser = true
    if(!validUser){
        res.statusCode = 400;
        res.setHeader('Content-Type', 'text/plain');
        /* Agora a mensagem de erro é genérica e não identifica fluxos da aplicação */
        res.end('Error: something went wrong with your request.')
        return
    }

    /* O valor no header Authorization agora está cifrado */
    res.setHeader('Content-Type', 'application/json');
    res.setHeader('Authorization', `Basic ${encrypt(faker.internet.password())}`);

    res.statusCode = 200;
    /* O retorno agora contém apenas dados de nome e estado do funcionário, 
    os únicos necessários no contrato da aplicação cliente da Union Corporation */
    res.end(JSON.stringify({
        name: faker.name.findName(),
        address: faker.address.state()
    }));
});


server.listen(port, localhost, () => {
    console.log(`Server running at http://${localhost}:${port}/employee`);
});