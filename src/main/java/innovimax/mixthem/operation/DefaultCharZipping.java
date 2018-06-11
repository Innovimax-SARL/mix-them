package innovimax.mixthem.operation;

import innovimax.mixthem.MixException;
import innovimax.mixthem.arguments.ParamValue;
import innovimax.mixthem.arguments.RuleParam;
import innovimax.mixthem.arguments.TokenType;
import innovimax.mixthem.io.IToken;
import innovimax.mixthem.io.ITokenRange;
import innovimax.mixthem.io.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
* <p>Zips two or more characters.</p>
* <p>Zipping stops when a character is missing.</p>
* @author Innovimax
* @version 1.0
*/
public class DefaultCharZipping extends AbstractTokenZipping {
	
	/**
	* Constructor
	* @param selection The file index selection (maybe empty)
 	* @param params The list of parameters (maybe empty)
	* @see innovimax.mixthem.arguments.RuleParam
	* @see innovimax.mixthem.arguments.ParamValue
	*/
	public DefaultCharZipping(final Set<Integer> selection, final TokenType tokenType, final Map<RuleParam, ParamValue> params) {
		super(selection, tokenType, params);
	}
	
	@Override
	public void process(final ITokenRange tokenRange, final ITokenResult result) throws MixException {
		//System.out.println("RANGE="+tokenRange.toString());
		/*final List<IToken> tokens = new ArrayList<IToken>();
		IntStream.range(0, tokenRange.size()).forEach(channel -> {
			if (channel > 0) {
				IntStream.range(0, this.sep.length()).forEach(index -> {
					tokens.add(Token.createCharToken((int) this.sep.charAt(index)));
				});
			}
			tokens.add(Token.createCharToken(tokenRange.getToken(channel).asCharacter()));

		});*/
		final List<IToken> tokens = IntStream.range(0, tokenRange.size())
				.mapToObj(channel -> {
					final List<IToken> subtokens = new ArrayList<IToken>();
					if (channel > 0) {
						IntStream.range(0, this.sep.length())
							.forEach(index -> {
								subtokens.add(Token.createCharToken((int) this.sep.charAt(index)));
							});
					}
					subtokens.add(Token.createCharToken(tokenRange.getToken(channel).asCharacter()));
					return subtokens.stream();
				})
				.flatMap(stream -> stream)
				.collect(Collectors.toList());
		if (tokens.size() > 0) {
			result.setResult(tokens);
		}
	}
	
}
