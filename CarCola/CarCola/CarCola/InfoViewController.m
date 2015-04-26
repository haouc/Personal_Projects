//
//  InfoViewController.m
//  CarCola
//
//  Created by Hao Zhou on 3/11/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "InfoViewController.h"

@interface InfoViewController ()

@end

@implementation InfoViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.sweepImage.hidden = YES;
    // Do any additional setup after loading the view.
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

- (IBAction)backButton:(id)sender {
    
        [self dismissViewControllerAnimated:YES completion:nil];
}

- (IBAction)rateButton:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thank you for your interest" message:@"Please give me a feedback how likely you would recommend this App to yur friends." delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:nil];
    [alert addButtonWithTitle:@"Rate"];
    [alert show];
}

#pragma ERASE USER SAVED DATA
//can be used in reset App saved data, and for final project function test.
- (IBAction)eraseButton:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Alert!" message:@"Please confirm you are willing to erase all saved information." delegate:self cancelButtonTitle:@"Cancel" otherButtonTitles:@"Yes", nil];
    [alert show];

}

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    if (buttonIndex == [alertView firstOtherButtonIndex]) {
        
        NSUserDefaults *sound = [NSUserDefaults standardUserDefaults];
        NSString *playAudio = [sound objectForKey:@"audio"];
        if ([playAudio integerValue]) {
            [self playSound:@"CleanShort"];
        }
        
        NSUserDefaults * userDefaults = [NSUserDefaults standardUserDefaults];
        NSDictionary * dict = [userDefaults dictionaryRepresentation];
        for (id key in dict) {
            [userDefaults removeObjectForKey:key];
        }
        [userDefaults synchronize];
        

        
        NSArray *imageNameArr = @[@"sweeping-1", @"sweeping-2", @"sweeping-3", @"sweeping-4", @"sweeping-5", @"sweeping-6"];
        
        NSMutableArray *imagesArr = [[NSMutableArray alloc] init];
        for (int i = 0; i < imageNameArr.count; i++) {
            [imagesArr addObject:[UIImage imageNamed:[imageNameArr objectAtIndex:i]]];
        }
        self.sweepImage.animationImages = imagesArr;
        self.sweepImage.animationDuration = 0.5;
        [self.view addSubview:_sweepImage];
        self.sweepImage.hidden = NO;
        [self.sweepImage startAnimating];
        _sweepImage.alpha = 1.0f;

        // Then fades it away after 2 seconds (the cross-fade animation will take 0.5s)
        [UIImageView animateWithDuration:0.5 delay:2.0 options:0 animations:^{
        // Animate the alpha value of your imageView from 1.0 to 0.0 here
            _sweepImage.alpha = 0.0f;

        } completion:^(BOOL finished) {
            // Once the animation is completed and the alpha has gone to 0.0, hide the view for good
            [_sweepImage stopAnimating];
            self.sweepImage.hidden = YES;
        }];

    }
}

#pragma HELP and App Info
- (IBAction)helpButton:(id)sender {
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Thanks for using this App" message:@"This is a neat App which was developed to assist people during their car purchase. You can collect the information about all cars that you are interested in, and our App can provide lots pretty pictures according to your choice. Your collected information can be recorded for future reference and you can even browser on edmunds for more info. Even when you are stuck somewhere no internet, our App can also show you many beautiful car pictures to enjoy." delegate:self cancelButtonTitle:@"I get it" otherButtonTitles: nil];

    [alert show];
}

- (IBAction)feedbackButton:(id)sender {
}

#pragma PLAY SOUND EFFECT
- (void)playSound:(NSString*)path{
    
    self.soundPath = [[NSBundle mainBundle]pathForResource:path ofType:@"wav"];
    self.audio = [[AVAudioPlayer alloc] initWithContentsOfURL:[NSURL fileURLWithPath:self.soundPath] error:NULL];
    self.audio.meteringEnabled = YES;
    self.audio.delegate = self;
    [self.audio prepareToPlay];
    [self.audio play];
}

@end
