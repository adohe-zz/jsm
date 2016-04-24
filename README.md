# jsm

jsm is a lightweight, embedded JVM stats monitor library. You can integrate it with kinds of java applications, which enables you to expose JVM stats easily.

## Example

```
public static void main(String[] args) throws Exception {
	// start the jsm server
    initStatsServer();

	// do something
}

private static void initStatsServer() {
	JSMServer jsmServer = new JSMServer(8088);
    jsmServer.addStatsSink(new PrintStatsSink("PrintStatsSink", 2000));
    jsmServer.start();
}

// Stats sink that just prints stats to stdout.
static class PrintStatsSink extends StatsSink {
	public PrintStatsSink(String name, long interval) {
    	super(name, interval);
    }

    @Override
    public void processClassStats(ClassStats classStats) {
    	System.out.printf("%s\n", classStats.toJsonStr());
    }

    @Override
    public void processGCStats(GCStats gcStats) {
    	System.out.printf("%s\n", gcStats.toJsonStr());
    }

    @Override
    public void processMemoryStats(MemoryStats memoryStats) {
    	System.out.printf("%s\n", memoryStats.toJsonStr());
    }

    @Override
    public void processThreadStats(ThreadStats threadStats) {
    	System.out.printf("%s\n", threadStats.toJsonStr());
    }

    @Override
    public void processOSStats(OSStats osStats) {
    	System.out.printf("%s\n", osStats.toJsonStr());
    }
}
```

## Licence

(The MIT License)

Copyright (c) 2010 [TonyAdo](https://github.com/AdoHe)

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
'Software'), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
