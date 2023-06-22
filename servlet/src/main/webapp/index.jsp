<html>
<head>
    <title>
        CalcApp
    </title>
</head>
<body>
<p>Please, enter operands and choose operation:</p>
<form method="get" action="calc" accept-charset="UTF-8">
    Operand 1:<br><label>
    <input type="text" name="operand1"/>
</label><br>
    Operand 2:<br><label>
    <input type="text" name="operand2"/>
</label><br>
    Select operation:<br>
    <input type="radio" id="sum" name="operation" value="+">
    <label for="sum">SUM</label><br>
    <input type="radio" id="dif" name="operation" value="-">
    <label for="dif">DIFFERENCE</label><br>
    <input type="radio" id="mul" name="operation" value="*">
    <label for="mul">MULTIPLY</label><br>
    <input type="radio" id="div" name="operation" value="/">
    <label for="div">DIVIDE</label><br>
    <input type="submit" value="Result">
</form>
</body>
</html>