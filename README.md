Bare-bones ScalaJS/React project used to demo ScalaJS/React.

To run, you'll need npm and sbt.

Once in sbt, run:

```
fastOptJS // this compiles our Scala code and transpiles it into javascript
fastOptJS::webpack // this grabs our JS dependencies via npm and puts them into a single file
```

Then open index.html in your browser.
