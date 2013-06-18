## Ruby, a scripting language
* interpreted
 * executed by an interpreter rather than a compiler
* object-oriented
 * encapsulation, data and behavior are packaged
 * inheritance, object types are organized in a class tree
 * polymorphism, objects can take many forms
* dynamically typed
 * types are bound at execution time rather than compile time
 * versus and trade-off for execution safety

## Yukihiro Matsumoto
* "an object-oriented language that combines characteristics from Lisp, Smalltalk, and Perl would be a great language to enhance our productivity."
* "blocks open up great possibilities in DSL and other features"
* "Remove the thread and add actors  or some other more advanced concurrency features"

## Author
* syntactic sugar, break the basic rules of language, to give friendly experience and understanding
* code blocks, marvelous effect in collections and elsewhere
* simplicity VS safety, productivity VS performance

## Day 1

#### Lightning Tour

#### Using Ruby with the Console
* One quote around a string means the string should be interpreted literally
* two quotes leads to string evaluation, substitution

#### The Programming Model
* What is the programming model?
* explore functional programming languages
* Scala mix programming models by throwing in object-oriented concepts.
* Stack-based, Logic-based, Prototype-based, ...
* Ruby is a pure object-oriented language

#### Decisions
* (if condition, statements, end), (statement if condition), if or unless
 * order.calculate_tax unless order.nil
* while, unless
* everything but nil and false evaluate to true. 0 is true!
* and(&&), or(||), &, |

#### Duck Typing
* Ruby is trongly typed, most of the time
* dynamic typing
 * Disadvantages: can't catch as many errors as you can with static typing.
 * don't have to inherit from the same parent to be used in the same way. 
* Duck typing is important when it comes to clean object-oriented esign. **Code to interfaces rather than implementations.**

#### What We Learned in Day 1

#### Day 1 Self-Study

Page **35/356**

## Day 2 Floating Down from the Sky

#### Defining Functions
* do not have to build a whole class to define a function
* every function returns something
* function is an object, pass functions as parameters to other functions

#### Arrays
 * ruby's workhorse ordered collection
 * use an array after it was an array
 * [] and []= are just syntactic sugar to allow access to an array
 * Arrays don't need to be homogeneous
 * multidimensional arrays are just arrays of arrays
 * Rich API on arrays. use array as q queue, linked list, stack, set

#### Hashes
 * hash can have any arbitrary key
 * :symbol, for naming things or ideas
 * simulate named parameters with a hash
 * can build some incredibly sophisticated data structures in Ruby. real power comes when you start to get into code blocks.

#### Code Blocks and yield
 * code block is a function without a name. pass it as a parameter to a fuction or a method.
 * {/} or do/end; Ruby convention is use braces when on one line and use the do/end form when more than one line
 * invoking the code block with yield
 * Blocks can also be first-class parameters. 
 * **use blocks to delay execution** execute_at_noon
 * **conditionally execute something** in_case_of_emergency
 * ruby is a block party. line of file, within a HTTP transaction

#### Running Ruby from a File
* move on to the reusable building blocks of Ruby programs

#### Defining Classes
* inherit from only one parent, superclass
* Module, Class, Object, everything inherits from Object
* Ruby call initialize when the class instantiates a new object
* prepend instance variables(one value per object) with @
* prepend class variables(one value per class) with @@
* instance variables and method names begin with lowercase letters in the underscore_style
* Functions and methods that test typically use a question mark(if test?)
* attr, instance variable, accessor
* attr_accessor, instance variable, accessor, setter

#### Writing a Mixin
* When the behaviors are not similar, Java uses interfaces to solve this problem.
* Ruby uses modules, a module is a collection of functions and constants.
* include a module as part of a class, those behaviors and constants become part of the class
* Module will often depend on several class methods. With Ruby, this contract is implicit, through duck typing
* Use a siplified single inheritance to define the essence of a class and then attach additional capabilities with modules
* Single inheritance plus mixins allow for a nice packaging of behavior

#### Modules, Enumerable, and Sets
* implement **each** to be enumerable
* implement **<=>** to be comparable, **spaceship**
* any? returns true if the condition is true for any of the elements
* all? returns true if the condition is true for all elements.
* sort, min, max, collect, map, find, select, find_all, inject
* **inject** takes a code block with two arguments and an expression

#### What We Learned in Day 2

#### Day 2 Self-Study
Page **48/356**

## Day 3 Serious Change

Metaprogramming means writing programs that write programs, 
**Active Record framework**


#### Open Classes
* With redefining freedom, you could refine any class or object at any time

#### Via method_missing
* Ruby calls a special debugging method when a method is missing
* much more difficult to debug, no longer tell you when a method is missing!

#### Modules
* DSL defines methods in a module that adds all the methods and constants needed to manage a class

- The interesting thing about all these metaprogramming techniques is that your programs can change based on the state of your application.

#### What We Learned in Day 3

## 2.5 Wrapping Up Ruby

* Core Strengths
* Scripting
* Web Development
* Time to Market
* Weakness
* Performance
* Concurrency and OOP
* Type Safety
* Final Thoughts
 





page end
