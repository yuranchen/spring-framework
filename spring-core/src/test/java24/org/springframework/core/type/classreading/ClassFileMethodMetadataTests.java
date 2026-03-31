package org.springframework.core.type.classreading;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.api.Test;

import org.springframework.core.type.MethodMetadata;

import static org.assertj.core.api.Assertions.assertThat;

class ClassFileMethodMetadataTests {

	@Test
	void getReturnTypeReturnsVoidForVoidReturnType() throws Exception {
		MethodMetadata metadata = new ClassFileMetadataReaderFactory(getClass().getClassLoader())
				.getMetadataReader(WithVoidMethod.class.getName())
				.getAnnotationMetadata()
				.getAnnotatedMethods(Tag.class.getName())
				.iterator().next();

		assertThat(metadata.getReturnTypeName()).isEqualTo("void");
	}

	@Test
	void getReturnTypeReturnsPrimitiveForPrimitiveReturnType() throws Exception {
		MethodMetadata metadata = new ClassFileMetadataReaderFactory(getClass().getClassLoader())
				.getMetadataReader(WithIntMethod.class.getName())
				.getAnnotationMetadata()
				.getAnnotatedMethods(Tag.class.getName())
				.iterator().next();

		assertThat(metadata.getReturnTypeName()).isEqualTo("int");
	}

	@Test
	void getReturnTypeReturnsReferenceTypeForReferenceReturnType() throws Exception {
		MethodMetadata metadata = new ClassFileMetadataReaderFactory(getClass().getClassLoader())
				.getMetadataReader(WithStringMethod.class.getName())
				.getAnnotationMetadata()
				.getAnnotatedMethods(Tag.class.getName())
				.iterator().next();

		assertThat(metadata.getReturnTypeName()).isEqualTo(String.class.getName());
	}

	@Test
	void getReturnTypeReturnsArrayTypeForArrayReturnType() throws Exception {
		MethodMetadata metadata = new ClassFileMetadataReaderFactory(getClass().getClassLoader())
				.getMetadataReader(WithStringArrayMethod.class.getName())
				.getAnnotationMetadata()
				.getAnnotatedMethods(Tag.class.getName())
				.iterator().next();

		assertThat(metadata.getReturnTypeName()).isEqualTo("java.lang.String[]");
	}

	@Retention(RetentionPolicy.RUNTIME)
	@interface Tag {}

	public static class WithVoidMethod {
		@Tag
		public void test() {}
	}

	public static class WithIntMethod {
		@Tag
		public int test() { return 0; }
	}

	public static class WithStringMethod {
		@Tag
		public String test() { return ""; }
	}

	public static class WithStringArrayMethod {
		@Tag
		public String[] test() { return new String[0]; }
	}

}
