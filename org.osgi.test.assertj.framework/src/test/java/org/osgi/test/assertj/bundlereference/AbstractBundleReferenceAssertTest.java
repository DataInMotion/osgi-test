package org.osgi.test.assertj.bundlereference;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.api.AssertFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleReference;
import org.osgi.test.assertj.testutil.AbstractAssertTest;

public abstract class AbstractBundleReferenceAssertTest<ASSERT extends AbstractBundleReferenceAssert<ASSERT, ACTUAL>, ACTUAL extends BundleReference>
	extends AbstractAssertTest<ASSERT, ACTUAL> {

	protected AbstractBundleReferenceAssertTest(AssertFactory<ACTUAL, ASSERT> factory, Class<ACTUAL> actualClass) {
		super(factory);
		this.actualClass = actualClass;
	}

	final protected Class<ACTUAL>	actualClass;
	protected Bundle				bundle;
	protected Bundle				otherBundle;

	@BeforeEach
	void setUp() {
		bundle = mock(Bundle.class);
		otherBundle = mock(Bundle.class);
		ACTUAL sut = mock(actualClass);
		when(sut.getBundle()).thenReturn(bundle);
		setActual(sut);
	}

	@Test
	void refersToBundle() {
		assertEqualityAssertion("bundle", aut::refersToBundle, bundle, otherBundle);
	}

	@Test
	void refersToBundleThat() {
		assertChildAssertion("bundle", aut::refersToBundleThat, actual::getBundle);
	}
}
