# Test Service
## Сервис для тестирования по математике

## Настройка БД
1. **Создать БД**
```
create database test-service;
```

2. **Запустить сервис**

Версия java

```
% java -version                                                                                          
openjdk version "20.0.1" 2023-04-18
OpenJDK Runtime Environment (build 20.0.1+10)
OpenJDK 64-Bit Server VM (build 20.0.1+10, mixed mode, sharing)
```

При запуске нужно указать расположение **application.yml**.

Пример запуска:
```
java -jar test-service-backend-0.0.1.jar --spring.config.location=/directory/application.yml 
```

3. **Запустить клиент**
```
dotnet build
dotnet run                                                                                     
```


4. **Swagger**

```
http://localhost:9090/test-service/swagger-ui/index.html#
```

5. **Авторизация**
```
login: admin
password: 111                                                                                   
```

6. **Отправка писем**
```
В application.yml указать данные почты, с которой будут отправляться письма пользователям.                                                                                
```
![Пользовательский интерфейс](img/interface.png)

