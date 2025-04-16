import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel


suspend fun main() = coroutineScope {
    try {
        // Список для хранения пользователей
        val users = mutableListOf<User>()

        // Количество пользователей
        val count = User().InputCountUser()

        // Канал для синхронизации ввода пользователей
        val flag = Channel<Unit>()


        // Создаем корутины для каждого пользователя
        val jobs = List(count) { i ->
            launch {
                // Ожидаем разрешения на ввод
                flag.receive()
                println("Пользователь ${i + 1}/$count")
                // Создаем и заполняем данные пользователя
                val newUser = User()
                newUser.CompletionUser()
                users.add(newUser)
                // Разрешаем ввод следующему пользователю, если это не последний
                if (i < count - 1) {
                    flag.send(Unit)
                }
                println("\n")
            }
        }
        // Процесс ввода первого пользователя
        flag.send(Unit)
        // Ожидаем завершения всех корутин
        jobs.joinAll()
        // Закрываем канал
        flag.close()

        MenuUsers(users)

    } catch (e: Exception) {
        println("Неверный формат ввода")
    }
}

    // Функция отображения меню
    fun MenuUsers(users: List<User>) {
        while (true) {
            println("---Меню---")
            println("Выберите действий: ")
            println("1 - Блокировка\n" +
                    "2 - Загрузить участников\n " +
                    "3 - Выход")
            val answer = readln().toInt()
            when(answer){
                1 -> runBlocking { delay(20L) }
                2 -> {
                    // Вывод отсортированного списка пользователей
                    println("\nСписок участников:")
                    users.sortedByDescending { it.countRep }.forEach {newUser ->
                        println("Имя пользователя: ${newUser.nickname}")
                        println("Пароль: ${newUser.password}")
                        println("Репозитории: ${newUser.countRep}\n")
                    }
                }
                3 -> return
                else -> println("Данного пункта нет")
            }
        }



    }