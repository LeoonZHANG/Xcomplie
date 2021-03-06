package rreil.disassembler.translators.avr8.translators;

import java.util.List;

import javalx.exceptions.UnimplementedException;
import rreil.disassembler.translators.avr8.common.AVR8OperationTranslator;
import rreil.disassembler.translators.common.TranslationCtx;
import rreil.lang.lowlevel.LowLevelRReil;
import rreil.lang.lowlevel.LowLevelRReilOpnd;

public class SbicTranslator extends AVR8OperationTranslator {
  public SbicTranslator () {
    super(ReturnType.None);
  }

  @Override public void emit (final TranslationCtx env, final LowLevelRReilOpnd dst, final LowLevelRReilOpnd src1, final LowLevelRReilOpnd src2, final List<LowLevelRReil> instructions) {
    throw new UnimplementedException();
  }
}
