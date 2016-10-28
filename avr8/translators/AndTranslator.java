package rreil.disassembler.translators.avr8.translators;

import java.util.List;

import rreil.disassembler.translators.avr8.common.AVR8OperationTranslator;
import rreil.disassembler.translators.avr8.emitters.LogicalOperationFlagEmitter;
import rreil.disassembler.translators.common.TranslationCtx;
import rreil.lang.lowlevel.LowLevelRReil;
import rreil.lang.lowlevel.LowLevelRReilFactory;
import rreil.lang.lowlevel.LowLevelRReilOpnd;

public class AndTranslator extends AVR8OperationTranslator {
  private static LowLevelRReilFactory factory = LowLevelRReilFactory.getInstance();

  public AndTranslator () {
    super(ReturnType.Register);
  }

  @Override public void emit (final TranslationCtx env, final LowLevelRReilOpnd dst, final LowLevelRReilOpnd src1, final LowLevelRReilOpnd src2, final List<LowLevelRReil> instructions) {
    instructions.add(factory.AND(env.getNextReilAddress(), dst, src1, src2));

    LogicalOperationFlagEmitter.$.emit(env, dst, src1, src2, instructions);
  }

}
