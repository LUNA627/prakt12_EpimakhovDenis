import kotlinx.coroutines.*

// Функция, имитирующая обработку нажатий
suspend fun Сlicking(n:Int) {
    for (i in 1.. n) {
        println("Нажмите Enter для обработки нажатия $i/$n")
        readln()
        delay(1000L)
    }

}

    // Функция вывода информации
    fun Сonclusion(n:Int) = runBlocking {
    for (i in 1..n) {
    println("Информация $i/$n...")
    delay(1000L)
    }
}

     fun main() = runBlocking {
         try {
             println("Введите количество раз n вывода функций")
             val n = readln().toInt()

            when{
                n < 0 -> println("число не может быть меньше 0")

                // Запуск двух корутин параллельно
                else -> {
                    val clickJob = launch { Сlicking(n) }  // Обработка нажатий
                    val СonclusJob = launch { Сonclusion(n) } // Получение данных

                    // Ожидание завершения обеих корутин
                    clickJob.join()
                    СonclusJob.join()

                    println("\nВсе операции завершены!")
                }
            }
         }
         catch (e:Exception) {
             println("Неверный формат ввода")
         }
}

