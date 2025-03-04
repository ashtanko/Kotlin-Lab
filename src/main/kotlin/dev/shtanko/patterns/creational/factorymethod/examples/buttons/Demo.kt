/*
 * Designed and developed by 2022 ashtanko (Oleksii Shtanko)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.shtanko.patterns.creational.factorymethod.examples.buttons

object Demo {
    @JvmStatic
    fun main(args: Array<String>) {
        val os = "Windows 10"
        runExample(os)
    }

    fun runExample(os: String): String {
        val dialog: Dialog = if (os == "Windows 10") {
            WindowsDialog()
        } else {
            HtmlDialog()
        }

        return dialog.renderWindow()
    }
}
