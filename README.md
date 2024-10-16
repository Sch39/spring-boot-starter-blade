# Spring Boot Starter Blade
Is template engine to use blade syntax on spring boot.

## Todo List Spring Boot Starter Blade

### Features
- Display data with escape [v]
- Display data with non-escape/passing raw html [v]
- Comment syntax [ ]
- @if, @ifelse, @else and @endif [ ]
- switch case [ ]
- Looping expression(fori and foreach) [ ]
- Conditional Classes & Styles
- Event attribute(@click, @input etc) [ ]

## File extension
This template engine only read template with `.blade.html` file.

## Features Completed
### Display data with escape `{{ variableName }}`
  example:

  data passed: `"name" => "<br>world"`
  will render as : `&lt;br&gt;world`

### Display data with non-escape `{!! variableName !!}`
  example:

  data passed: `"name" => "<br>world"`
  will render as : `<br>world`

## Example
Example how to used are in `src/main/java/dev/sch39/controllers/` for how to passed variable/data and template in `src/main/resources/templates/`