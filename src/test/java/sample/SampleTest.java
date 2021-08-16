package sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.*;

class SampleTest {
	// The class we're testing.
	@InjectMocks
	private SampleClass sampleClass;
	
	// A dependency of the class we're testing.
	@Mock
	private SampleDependency dependency;
	
	// Holds our mocks for closing. We don't use this object ourselves.
	private AutoCloseable closeable;
	
	@BeforeEach
	void initialize() {
		// Instantiate our mocks and inject their dependencies.
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void cleanup() throws Exception {
		closeable.close();
	}
	
	@Test
	void testSampleMethodThrowsIllegalArgumentExceptionWhenStringIsNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			sampleClass.sampleMethod(null);	
		});
	}
	
	@Test
	void testSampleMethodReturnsEmptyStringWhenStringIsEmptyString() {
		String result = sampleClass.sampleMethod("");
		assertTrue(result.isEmpty());
	}
	
	@Test
	void testSampleMethodReturnsCorrectResult() {
		// Given - set up our precondition
		String expectedResult = "sample result";
		
		given(dependency.doSomething(anyString())).willReturn(expectedResult);
		
		// When - call our method
		String actualResult = sampleClass.sampleMethod("sample request");
		
		// Then - check the results
		assertEquals(expectedResult, actualResult);
	}
}
