package de.mwvb.jxlsct;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import de.mwvb.ct3.Transformator;

public class ScriptTest {

	@Test
	public void each_easy() throws IOException {
		String text = "<jx:forEach items=\"list\">";
		String result = new Transformator().transform(text);
		Assert.assertEquals("jx:each(items=\"list\", var=\"\", lastCell=\"\")", result);
	}

	@Test(expected = RuntimeException.class)
	public void each_itemsMissing() throws IOException {
		String text = "<jx:forEach >";
		new Transformator().transform(text);
	}

	@Test
	public void each_whitespace() throws IOException {
		String text = "<jx:forEach items = \"${filterMap.filterParameters}\" var = \"parameter\"  >";
		String result = new Transformator().transform(text);
		Assert.assertEquals("jx:each(items=\"filterMap.filterParameters\", var=\"parameter\", lastCell=\"\")", result);
	}

	@Test
	public void each_select() throws IOException {
		String text = "<jx:forEach var=\"e\" select=\"foo==1\" items=\"list\">";
		String result = new Transformator().transform(text);
		Assert.assertEquals("jx:each(items=\"list\", var=\"e\", select=\"foo==1\", lastCell=\"\")", result);
	}
	
	@Test
	public void if_easy() throws IOException {
		String text = "<jx:if test=\"aaa==1\">";
		String result = new Transformator().transform(text);
		Assert.assertEquals("jx:if(condition=\"aaa==1\", lastCell=\"\")", result);
	}
	
	@Test
	public void sum() throws IOException {
		String text = "${SUM(month):group.items}";
		String result = new Transformator().transform(text);
		Assert.assertEquals("${xmap:sum(\"month\", \"g.group\")}", result);
	}
}
