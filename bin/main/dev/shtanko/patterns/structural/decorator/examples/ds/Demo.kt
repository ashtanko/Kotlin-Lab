/*
 * Copyright 2022 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.structural.decorator.examples.ds

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000"
        val encoded: DataSourceDecorator = CompressionDecorator(
            EncryptionDecorator(
                FileDataSource("out/OutputDemo.txt"),
            ),
        )
        encoded.writeData(salaryRecords)
        val plain: DataSource = FileDataSource("out/OutputDemo.txt")

        println("- Input ----------------")
        println(salaryRecords)
        println("- Encoded --------------")
        println(plain.readData())
        println("- Decoded --------------")
        println(encoded.readData())
    }
}
