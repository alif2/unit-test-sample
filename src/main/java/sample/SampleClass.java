package sample;

public class SampleClass {
	private SampleDependency dependency;

	public SampleClass(SampleDependency dependency) {
		this.dependency = dependency;
	}
	
	String sampleMethod(String s) {
		if(s == null) {
			throw new IllegalArgumentException(s);
		} else if(s.isEmpty()) {
			return "";
		}
		
		StringBuilder sb = buildRequest(s);
		return dependency.doSomething(sb.toString());
	}

	private StringBuilder buildRequest(String s) {
		StringBuilder sb = new StringBuilder(s);
		sb.append("foo=foo2");
		sb.append("bar=baz");
		return sb;
	}
}
