// Класс для хранения данных пользователя
class User {
    var nickname = ""
    var password = ""
    var countRep = 0

    // Функция ввода количества пользователей
    fun InputCountUser():Int {
        print("Введите количество пользователей: ")
        val countUser = readln().toInt()

        when{
            countUser > 0 -> return countUser
            else -> return 1
        }

    }

    // Функция заполнения данных пользователя
     fun CompletionUser() {
        try {
            println("Введите имя пользователя: ")
            nickname = readln()
            println("Введите пароль: ")
            password = readln()
            println("Введите число репозиториев: ")
            countRep = readln().toInt().let { input ->
                when {
                    input < 0 -> Math.abs(input)
                    else -> input
                }
            }
        }
       catch (e:Exception) {
           println("Неверный формат ввода")
       }
    }



}