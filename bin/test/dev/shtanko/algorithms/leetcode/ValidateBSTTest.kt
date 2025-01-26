/*
 * Copyright 2021 Oleksii Shtanko
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

package dev.shtanko.algorithms.leetcode

import java.util.stream.Stream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

abstract class ValidateBSTTest<out T : ValidateBinarySearchTree>(private val strategy: T) {
    private class InputArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(p0: ExtensionContext?): Stream<out Arguments> = Stream.of(
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(1)
                    right = TreeNode(3)
                },
                true,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(1)
                    right = TreeNode(4).apply {
                        left = TreeNode(3)
                        right = TreeNode(6)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(4).apply {
                    left = TreeNode(2).apply {
                        left = TreeNode(1)
                        right = TreeNode(3)
                    }
                    right = TreeNode(5)
                },
                true,
            ),
            Arguments.of(
                TreeNode(1),
                true,
            ),
            Arguments.of(
                TreeNode(6).apply {
                    left = TreeNode(7).apply {
                        left = TreeNode(1).apply {
                            left = TreeNode(13).apply {
                                left = TreeNode(11)
                                right = TreeNode(5)
                            }
                        }
                        right = TreeNode(4)
                    }
                    right = TreeNode(9).apply {
                        left = TreeNode(8).apply {
                            left = TreeNode(3)
                            right = TreeNode(12).apply {
                                right = TreeNode(14).apply {
                                    left = TreeNode(10)
                                }
                            }
                        }
                        right = TreeNode(2)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(1).apply {
                    left = TreeNode(3)
                    right = TreeNode(8).apply {
                        left = TreeNode(2)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(8).apply {
                    left = TreeNode(3).apply {
                        right = TreeNode(2)
                    }
                    right = TreeNode(6)
                },
                false,
            ),
            Arguments.of(
                TreeNode(22).apply {
                    left = TreeNode(18).apply {
                        right = TreeNode(19)
                        left = TreeNode(9)
                    }
                    right = TreeNode(32).apply {
                        right = TreeNode(45).apply {
                            right = TreeNode(50)
                            left = TreeNode(40)
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(300).apply {
                    left = TreeNode(290).apply {
                        left = TreeNode(280).apply {
                            left = TreeNode(271).apply {
                                left = TreeNode(260).apply {
                                    left = TreeNode(255).apply {
                                        left = TreeNode(250).apply {
                                            right = TreeNode(251)
                                        }
                                        right = TreeNode(256).apply {
                                            right = TreeNode(258).apply {
                                                left = TreeNode(257)
                                            }
                                        }
                                    }
                                }
                            }
                            right = TreeNode(282).apply {
                                left = TreeNode(281)
                                right = TreeNode(285)
                            }
                        }
                        right = TreeNode(295)
                    }
                    right = TreeNode(1000).apply {
                        left = TreeNode(900).apply {
                            right = TreeNode(990).apply {
                                left = TreeNode(950).apply {
                                    right = TreeNode(980)
                                }
                            }
                        }
                        right = TreeNode(2000).apply {
                            right = TreeNode(3000).apply {
                                right = TreeNode(4000).apply {
                                    left = TreeNode(3500)
                                    right = TreeNode(5000).apply {
                                        left = TreeNode(4300).apply {
                                            right = TreeNode(4700).apply {
                                                right = TreeNode(4900)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    left = TreeNode(7).apply {
                        left = TreeNode(2)
                        right = TreeNode(6).apply {
                            left = TreeNode(5)
                            right = TreeNode(11)
                        }
                    }
                    right = TreeNode(5).apply {
                        right = TreeNode(9).apply {
                            left = TreeNode(4)
                        }
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(6).apply {
                    right = TreeNode(8).apply {
                        left = TreeNode(7)
                        right = TreeNode(9)
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(6).apply {
                    right = TreeNode(8).apply {
                        left = TreeNode(5)
                        right = TreeNode(9)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    right = TreeNode(3).apply {
                        left = TreeNode(1)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(2),
                true,
            ),
            Arguments.of(
                TreeNode(2).apply {
                    right = TreeNode(1)
                },
                false,
            ),
            Arguments.of(
                TreeNode(3).apply {
                    left = TreeNode(4)
                },
                false,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        right = TreeNode(6)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        right = TreeNode(5)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(5).apply {
                    left = TreeNode(4).apply {
                        right = TreeNode(6)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        right = TreeNode(6)
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        right = TreeNode(8)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(4)
                        right = TreeNode(6)
                    }
                },
                true,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(4)
                        right = TreeNode(8)
                    }
                },
                false,
            ),
            Arguments.of(
                TreeNode(7).apply {
                    left = TreeNode(5).apply {
                        left = TreeNode(4)
                        right = TreeNode(6)
                    }
                    right = TreeNode(9).apply {
                        left = TreeNode(8)
                    }
                },
                true,
            ),
        )
    }

    @ParameterizedTest
    @ArgumentsSource(InputArgumentsProvider::class)
    fun `is valid BST test`(root: TreeNode, expected: Boolean) {
        val actual = strategy.invoke(root)
        assertThat(actual).isEqualTo(expected)
    }
}

class RecursiveTraversalValidRangeTest : ValidateBSTTest<RecursiveTraversalValidRange>(RecursiveTraversalValidRange())
class IterativeTraversalValidRangeTest : ValidateBSTTest<IterativeTraversalValidRange>(IterativeTraversalValidRange())
class RecursiveInorderTraversalTest : ValidateBSTTest<RecursiveInorderTraversal>(RecursiveInorderTraversal())
class IterativeInorderTraversalTest : ValidateBSTTest<IterativeInorderTraversal>(IterativeInorderTraversal())
