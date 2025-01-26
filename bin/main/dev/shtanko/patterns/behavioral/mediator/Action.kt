/*
 * Copyright 2020 Oleksii Shtanko
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

package dev.shtanko.patterns.behavioral.mediator

enum class Action(val title: String, val description: String) {
    HUNT("hunted a rabbit", "arrives for dinner"),
    TALE("tells a tale", "comes to listen"),
    GOLD("found gold", "takes his share of the gold"),
    ENEMY("spotted enemies", "runs for cover"),
    NONE("", ""),
}
