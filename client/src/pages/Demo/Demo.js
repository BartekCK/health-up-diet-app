import React from 'react';
import { Container } from 'react-bootstrap';
import { ProgressCircle } from '../../components/progress-circle/ProgressCircle';
import { Color } from '../../helpers/enums/Colors';

export const DemoPage = () => {
  return (
    <Container>
      <div>
        <h1>Progress circle</h1>
        <ProgressCircle
          name='Tłuszcze'
          actual={97.12}
          dailyNutrients={100}
          color={Color.blue}
        />
      </div>
    </Container>
  );
};
